package esercizio1;

public class Prova{
    public static void main(String[] args) {
        Deposito d = new Deposito(2);
        Produttore p1 = new Produttore(Tipo.X, d);
        Produttore p2 = new Produttore(Tipo.Y, d);

        Consumatore c1 = new Consumatore(d);
        Controllore x = new Controllore(d);
        p1.start();
        p2.start();
        c1.start();
        x.start();
    }
}