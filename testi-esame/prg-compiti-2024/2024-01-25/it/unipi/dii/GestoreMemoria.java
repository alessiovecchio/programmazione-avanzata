package it.unipi.dii;

public class GestoreMemoria {

	private Blocco[] blc;
	private int disponibili;
	private int inAttesa;
	private final int dim_blocco;
	

	public GestoreMemoria(int m, int n) {
		if(m % n != 0)
			throw new IllegalArgumentException();
		int q = m / n;
		blc = new Blocco[q];
		for(int i=0; i<q; i++)
			blc[i] = new Blocco();	
		disponibili = q;
		dim_blocco = n;
	}

	public synchronized Blocco acquisisci(boolean prio) throws InterruptedException {
		if(prio)
			inAttesa++;
		while(disponibili == 0 || (!prio && inAttesa >0)) {
			wait();
		}
		if(prio)
			inAttesa--;
		Blocco l = null; 
		for(int i=0; i<blc.length; i++)
			if(blc[i].isLibero()) {
				l = blc[i];
				break;
			}
		l.occupa();
		disponibili--;
		return l;
	}	

	public synchronized void free(Blocco l) {
		disponibili++;
		l.libera();
		notifyAll();
	}
	
	public synchronized void istalla(int k) {
		if(k % dim_blocco != 0)
			throw new IllegalArgumentException();
		int z = k / dim_blocco;	
		disponibili += z;
		Blocco[] tmp = new Blocco[blc.length + z];
		for(int i=0; i<blc.length; i++)
			tmp[i] = blc[i];
		for(int i=0; i<z; i++) 
			tmp[blc.length+i] = new Blocco();
		blc = tmp;
		notifyAll();
	}
}
