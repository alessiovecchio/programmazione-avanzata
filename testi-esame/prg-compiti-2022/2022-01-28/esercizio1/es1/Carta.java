package es1;

public class Carta {

    private Seme seme;
    private int numero;

    /**
     * Crea una carta
     * @param n numero della carta
     * @param s seme della carta
     */
    public Carta(int n, Seme s){
        numero = n;
        seme = s;
    }

    public String toString() {
        return numero+seme.toString();
    }

    /**
     * Restituisce true se le carte sono uguali, false
     * altrimenti
     * 
     * @param c l'altra carta
     * @return true se uguali, false altrimenti
     */
    public boolean match(Carta c) {
        return numero == c.numero && seme == c.seme;
    }
}