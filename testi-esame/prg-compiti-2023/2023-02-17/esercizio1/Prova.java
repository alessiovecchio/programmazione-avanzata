package esercizio1;

public class Prova {
	public static void main(String[] args) {
		Partita l = new Partita(4);
		new Giocatore(l, Seme.CUORI).start();
		new Giocatore(l, Seme.QUADRI).start();
		new Giocatore(l, Seme.PICCHE).start();
		new Giocatore(l, Seme.FIORI).start();
	}
}
