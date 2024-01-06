package esercizio1;

public class Consumatore extends Thread {
   
    private Deposito d;
   
    public Consumatore(Deposito d) {
        this.d = d;
    }
    public void run(){
        try {
            while(true) {
                Pezzo[] a = d.estrai();
                System.out.println("Ho estratto " + a[0] + " e " + a[1]);
            }
        } catch(InterruptedException ie) {
            System.out.println("Sono stato interrotto");
        }
    }
}