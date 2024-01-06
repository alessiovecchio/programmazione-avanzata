package esercizio1;

public class Lancia {
	public static void main(String[] args) {
		try {
			// Password cifrata
			String l = args[0];
			// Numero di thread
			int q = Integer.parseInt(args[1]);
			// Gli argomenti successivi sono le possibili password in chiaro
			String[] s = new String[args.length - 2];
			for(int i=0; i<args.length-2; i++)
				s[i] = args[i+2];
			// Creo l'analizzatore
			Cracker d = new Cracker(l);
			// Comincia la ricerca
			d.controlla(s, q);
			// Stampa il risultato (eventualmente attendendo che sia pronto)
			d.stampaRisultato();
		} catch (InterruptedException ie) {
			System.out.println("Sono stato interrotto...");
		}
	}
}
