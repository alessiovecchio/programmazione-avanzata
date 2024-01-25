package it.unipi.dii;

public class Processo extends Thread {

	private boolean prio;
	private GestoreMemoria gm;

	public Processo(GestoreMemoria sl, boolean prio) {
		this.gm = sl;
		this.prio = prio;
	}

	public void run(){
			try {
				Blocco l = gm.acquisisci(prio);
				System.out.println(getName() + ": ho preso blocco n. " + l.getNumero());
				sleep((int)(Math.random()*1000));
				gm.free(l);
				sleep((int)(Math.random()*1000));
			} catch (InterruptedException ie) {
        System.out.println("Sono stato interrotto...");		
			}
	}
}
