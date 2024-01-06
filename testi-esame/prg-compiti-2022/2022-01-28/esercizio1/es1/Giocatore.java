package es1;

import java.util.ArrayList;
import java.util.List;

/**
 * Giocatore
 */
public class Giocatore extends Thread {
    // Mazzi di carte
    private Mazzo sinistra;
    private Mazzo destra;

    // Numero di carte che il giocatore deve avere in mano
    private static final int NUM_CARTE = 5;
    // Le carte che il giocatore ha in mano
    private List<Carta> carte = new ArrayList<Carta>();
    
    // Punti del giocatore
    private int punti;
    
    // Oggetto condiviro per capire chi vince nel caso in cui arrivino entrambi 
    // contemporaneamente a 15 punti
    private Vittoria vittoria;

    /**
     * Crea un giocatore
     * 
     * @param nome nome del giocatore
     * @param s mazzo di sinistra
     * @param d mazzo di destra
     * @param v oggetto condiviso per capire chi vince in caso di parità
     */
    public Giocatore(String nome, Mazzo s, Mazzo d, Vittoria v) {
        super(nome);
        sinistra = s;
        destra = d;
        vittoria = v;
    }

    /**
     * Controlla se in mano ha due carte uguali e nel caso le rimuove dal gioco
     */
    private void controlla() {
        System.out.println(getName() + ": ho " + carte.size() + " carte " + carte.toString());    

        for(int i=0; i<carte.size()-1; i++)
            for(int j=i+1; j<carte.size(); j++)
                if(carte.get(i).match(carte.get(j))) {
                    Carta tmp = carte.remove(j);
                    carte.remove(i);
                    punti++;
                    System.out.println(getName() + ": coppia di " + tmp);
                }
    }

    /**
     * Scarta una delle carte che ha in mano (scelta a caso)
     * @return
     */
    private Carta scarta() {
        int i = (int)(Math.random() * carte.size());
        return carte.remove(i);
    }

    public void run(){
        try {

esterno:    while(true) {
                controlla();
                if(punti >= 15) {
                    sinistra.fine();
                    break;
                }
                if(carte.size()>0) {
                    Carta s = scarta();
                    sinistra.metti(s);
                }
                int quante = NUM_CARTE - carte.size();
                for(int i = 0; i<quante; i++) {
                    Carta p = destra.pesca();
                    if(p == null){
                        break esterno;
                    }
                    carte.add(p);
                }
            }

            if(punti >= 15 && vittoria.hoVinto()) {
                System.out.println(getName() + ": ho vinto, ho " + punti + " punti");
            } else {
                System.out.println(getName() + ": ho perso, ho " + punti + " punti");
            }
        } catch (InterruptedException ie){
            System.out.println(getName() + ": sono stato interrotto");
        }
    }
}