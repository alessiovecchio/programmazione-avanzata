package esercizio1;

public class Produttore extends Thread {
   
    private Tipo t;
    private Deposito d;
   
    public Produttore(Tipo t, Deposito d) {
        this.t = t;
        this.d = d;
    }
    public void run(){
        try {
            while(true) {
                Pezzo p = new Pezzo(t);
                d.inserisci(p);
            }
        } catch(InterruptedException ie) {
            System.out.println("Sono stato interrotto");
        }
    }
}