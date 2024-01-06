package esercizio1;

public class Parcheggio {

	private final int n;
	private final boolean[] attendo; 
	private int inAttesa;
	private int ins, est; 

	/**
	 * Crea un parcheggio con n posti
	 */
	public Parcheggio(int n) {
		this.n = n;
		attendo = new boolean[n]; 
	}

	public synchronized void attendi() throws InterruptedException, ParcheggioPienoException {
		// Se il parcheggio e' pieno, esce con un eccezione
		if(inAttesa == n) { 
			System.out.println(Thread.currentThread().getName() + ": parcheggio pieno");
			throw new ParcheggioPienoException();
		}
		// Nuova auto in attesa
		inAttesa++;
		// Questo e' il posto occupato dall'auto
		int mioIns = ins;
		// Indica che il posto e' occupato
		attendo[ins] = true;
		// Sposta l'indice ins al posto che la prossima auto dovra' occupare
		ins = (ins + 1) % n;
		// Risveglia l'operaio
		notifyAll();
		// Aspetta il lavaggio
		while(attendo[mioIns]) {
			System.out.println(Thread.currentThread().getName() + 
                                           ": sono nel posto " + mioIns);
			wait();
		}
		System.out.println(Thread.currentThread().getName() + ": vado via");
	}

	public synchronized void lava() throws InterruptedException {
		// Se non c'e' un'auto da lavare si blocca
		while(!attendo[est]) {
			System.out.println("Mi fermo non ci sono auto da lavare");
			wait();
		}
		// Lava l'auto
		System.out.println("Lavo la macchina nel posto " + est);
		// Posto libero
		attendo[est] = false;
		est = (est + 1) % n;
		inAttesa--;
		notifyAll();
	}
}
