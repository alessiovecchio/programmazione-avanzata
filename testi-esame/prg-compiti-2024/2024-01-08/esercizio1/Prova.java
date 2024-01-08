package esercizio1;

public class Prova {
    public static void main(String[] args) {
        Negozio s = new Negozio(3, 2);
        for(int i=0; i<10; i++)
            new Cliente(s, i%2==0).start();
    }
}