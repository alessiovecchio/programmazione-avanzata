package esercizio1;

import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.io.UnsupportedEncodingException;

public class Cracker {

	/* La password cifrata */
	private String pwdCifrata;
	/* Le possibili password (le stringhe s) */
	private String[] possibiliPassword;
	/* Mi dice se quella possibile password è già stata controllata o meno */
	private boolean[] controllata;
	/* Quante possibili password rimangono da controllare */
	private int daControllare;
	/* Ho trovato la password, la memorizzo qui */
	private String bingo;

	/**
	 * Crea un Cracker in cui la password cifrata è quella specificata come
	 * argomento
	 */
	public Cracker(String s) {
		pwdCifrata = s;
	}

	/* Fa partire la ricerca usando un numero di thread pari a numThreads */
	public void controlla(String[] possibiliPassword, int numThreads) {
		this.possibiliPassword = possibiliPassword;
		daControllare = possibiliPassword.length;
		controllata = new boolean[daControllare];
		// Attiva i thread
		for (int i = 0; i < numThreads; i++) {
			new Cercatore().start();
		}
	}

	/*
	 * Stampa il risultaato, eventualmente attendendo che tutti abbiano finito
	 */
	public synchronized void stampaRisultato() throws InterruptedException {
		// Attende che tutti abbiano finito
		while (!finito())
			wait();
		if (bingo != null)
			System.out.println("La password è " + bingo);
		else
			System.out.println("Nessuna delle parole è la password");
	}

	/*
	 * Restituisce true se non ci sono piu' stringhe da controllare o se la password è
	 * stata trovata, false altrimenti
	 */
	private synchronized boolean finito() {
		return daControllare == 0 || bingo != null;
	}

	/* Chiamato ogni volta che un thread ha terminato il controllo di una stringa */
	private synchronized void fatto() {
		daControllare--;
		if (daControllare == 0 || bingo != null)
			notify();
	}

	/*
	 * Restituisce l'indice di un vocabolo da controllare, -1 se non ce ne sono più o se la
	 * password è stata già trovata
	 */
	private synchronized int indiceVocabolo() {
		if (finito())
			return -1;
		// Scorro i vocaboli
		for (int i = 0; i < controllata.length; i++)
			// Se il vocabolo i-esimo deve essere ancora cercato restituisco i e metto
			// il booleano corrispondente a true
			if (!controllata[i]) {
				controllata[i] = true;
				return i;
			}
		return -1;
	}

	public static boolean check(String cifrata, String possibile)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return Hash.hash(possibile).equals(cifrata);
	}

	// Questa soluzione si basa su una classe interna, ma si poteva anche usare una
	// classe totalmente separata
	class Cercatore extends Thread {

		public void run() {
			int k;
			try {
				// Cicla finche' ci sono vocabili da cercare
				while ((k = indiceVocabolo()) >= 0) {
					// Prende il vocabolo da cercare
					String v = possibiliPassword[k];
					System.out.println(getName() + ": controllo " + v);
					// Calcola l'hash e verifica se uguale
					boolean r = check(pwdCifrata, v);
					if (r)
						bingo = v;
					fatto();
				}
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
				return;
			}
		}
	}

}
