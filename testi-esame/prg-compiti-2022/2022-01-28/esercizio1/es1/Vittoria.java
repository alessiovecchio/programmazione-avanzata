package es1;

public class Vittoria {
    
    private boolean vinto = true;

    /**
     * Il primo giocatore che chiama hoVinto() ottiene true come
     * valore di ritorno. Il secondo ottiene false.
     * @return true o false per indicare se il giocatore ha vinto o 
     * meno
     */
    public synchronized boolean hoVinto() {
        boolean tmp = vinto;
        vinto = false;
        return tmp;
    }
}