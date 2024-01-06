package es1;

public class Lancia {

    public static void main(String[] args) {

        // I due mazzi
        Mazzo m1 = new Mazzo();
        Mazzo m2 = new Mazzo();
        // Usato per capire chi vince nel caso in cui 
        // i due giocatori arrivino contemporaneamente a 15
        Vittoria v = new Vittoria();

        // Crea i due giocatori
        Giocatore g1 = new Giocatore("G1", m1, m2, v);
        Giocatore g2 = new Giocatore("G2", m2, m1, v);

        // Avvia i thread
        g1.start();
        g2.start();

    }
}