package esercizio1;

public class Prova{
    public static void main(String[] args) {
        Deposito d = new Deposito(10);
        Produttore p1 = new Produttore(Tipo.A, d);
        Produttore p2 = new Produttore(Tipo.B, d);
        Consumatore c1 = new Consumatore(d);
        Controllore x = new Controllore(d);
        p1.start();
        p2.start();
        c1.start();
        x.start();
    }
}