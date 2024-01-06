package es1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazzo {

    // Le carte del mazzo sono contenute in una List
    private List<Carta> c = new ArrayList<Carta>();
    // Usato per capire se un giocatore è arrivato a 15, in modo
    // che l'altro possa terminare la propria esecuzione
    private boolean fine;

    /**
     * Crea un mazzo con 40 carte
     */
    public Mazzo() {
        for(int i=1; i<=10; i++) {
            for(Seme s: Seme.values()) {
                c.add(new Carta(i, s));
            }
        }
        // Mescola il mazzo
        Collections.shuffle(c);
    }

    /**
     * Pesca una carta dal mazzo. Il metodo è bloccante nel caso
     * in cui il mazzo sia vuoto.
     * 
     * @return la carta pescata o null se l'altro giocatore è arrivato a 15
     * @throws InterruptedException
     */
    public synchronized Carta pesca() throws InterruptedException {
        while(c.size() == 0 && !fine) {
            wait();
        }
        if (fine) 
            return null;
        return c.remove(0);
    }

    /**
     * Indica all'altro giocatore che ha finito
     */
    public synchronized void fine() {
        fine = true;
        notify();
    }

    /**
     * Mette una carta nel mazzo
     * @param e la carta che viene inserita nel mazzo
     */
    public synchronized void metti(Carta e) {
        c.add(e);
        notify();
    }

    /**
     * Restituisce una rappresentazione del mazzo come stringa
     */
    public String toString(){
        return c.toString();
    }

} 