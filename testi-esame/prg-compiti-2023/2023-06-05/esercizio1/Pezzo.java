package esercizio1;

public class Pezzo {

    private Tipo tipo;
    private double qualita;

    public Pezzo(Tipo t) {
        tipo = t;
        qualita = Math.random();
    }

    public Tipo getTipo(){
        return tipo;
    }

    public double getQualita(){
        return qualita;
    }

    public String toString() {
        return "<" + tipo + " " + String.format("%.2f", qualita) + ">";
    }
}