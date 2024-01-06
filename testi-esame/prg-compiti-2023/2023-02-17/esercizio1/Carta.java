package esercizio1;

public class Carta {
    
    private int v;
    private Seme s;

    public Carta(int v, Seme s) {
        this.v = v;
        this.s = s;
    }

    public Seme getSeme(){
        return s;
    }

    public int getValore(){
        return v;
    }

    public static int numeroOrdine(int v, Seme s) {
        return s.ordinal()*10+v;
    }

    public boolean equals(Object o) {
        if(o == null || !(o instanceof Carta))
            return false;
        Carta c = (Carta) o;
        return v == c.v && s == c.s; 
    }

    public String toString() {
        return v + s.toString();
    }
}
