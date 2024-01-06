public class Campeggio {

	private static final int DEFAULT_NUM = 200;
	private int n;
	private int[] prenotazioni;
	
	public Campeggio(){
		this(DEFAULT_NUM);
	}
	
	public Campeggio(int n) {
		this.n = n;
		prenotazioni = new int[n];
	}

	public synchronized int prenota(int q, long t) throws InterruptedException,PrenotazioneFallitaException {
		boolean expired = false;
        	long elapsed = 0;
		while(q>n && !expired) {
			long time = System.currentTimeMillis();
			wait(t - elapsed);
			elapsed += System.currentTimeMillis() - time;
			expired = elapsed >= t;
		}	
		if(q>n) 
			throw new PrenotazioneFallitaException();
		int i;
		for(i=0; prenotazioni[i]>0; i++);
		prenotazioni[i] = q;
		n -= q;
		return i;	
	}

	public synchronized void disdici(int p) {
		n += prenotazioni[p];
		prenotazioni[p] = 0;
		notifyAll();
	}
}
