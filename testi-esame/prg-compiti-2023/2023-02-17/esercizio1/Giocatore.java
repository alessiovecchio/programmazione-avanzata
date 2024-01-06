package esercizio1;

import java.util.Arrays;

public class Giocatore extends Thread {

	private Partita l;
	private Seme s;
	private Carta[] c = new Carta[10];

	public Giocatore(Partita l, Seme s) {
		this.l = l;
		this.s = s;
	}
	
	public void run() {
		try {
			int prese = 0;
			while(prese<10) {
				try {
					Carta[] disp = l.carteDisponibili();
					Carta scelta = disp[(int)(Math.random()*disp.length)];
					c[prese] = l.compraCarta(scelta.getValore(), scelta.getSeme());
					prese++;
				} catch (CartaNonDisponibileException ce) {
					System.out.println("Carta non più disponibile");
				}
			}
			System.out.println(getName() + ": ho queste carte " + Arrays.toString(c));
			boolean ris = l.attendiEstrazione(c);
			System.out.println(ris? getName() + ": Ho vinto" : getName() + ": Non ho vinto");
		} catch(InterruptedException ie) {
			System.out.println("Interrotto");
		}
	}
}
