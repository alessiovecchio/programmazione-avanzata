public class Sala {
	private final int N;
	private final int K;
	private int[] p = new int[5];	// numero di visitatori in attesa per i vari livelli di priorità
	private int n;	// numero di visitatori attualmente nella sala
	private boolean chiuso;

	public Sala(int N, int K) {
		if (N <= 0) {
			throw new IllegalArgumentException("N non valido");
		}
		if (K < 0 || K >= N) {
			throw new IllegalArgumentException("K non valido");
		}
		this.N = N;
		this.K = K;
	}

    private int aMaggiorPriorita(int c) {
		int totale = 0;
        for (int i = 0; i < c; i++) {
            totale += p[i];
        }
        return totale;
    }

	private int postiLiberi() {
		return N - n;
	}

	public synchronized void entrata(int codice) throws InterruptedException {
		p[codice]++;
		while (chiuso || aMaggiorPriorita(codice) >= postiLiberi()) {
			System.out.println("Paziente " + Thread.currentThread().getName()  + " con codice " + codice + " in attesa");
			wait();
		}
		System.out.println("Paziente " + Thread.currentThread().getName()  + " con codice " + codice + " entrato");

		p[codice]--;
		n++;
		chiuso = (n == N);
		if(chiuso) {
			System.out.println("Sala chiusa");
		} 
	}

	public synchronized void uscita() {
		n--;
		System.out.println("Paziente " + Thread.currentThread().getName() + " uscito");

		if (chiuso && n == N - K) {
			chiuso = false;
			System.out.println("Sala riaperta");
			notifyAll();
		}
	}
}		
