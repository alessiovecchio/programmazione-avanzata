package esercizio1;

public class Controllore extends Thread {
   
    private Deposito d;
   
    public Controllore(Deposito d) {
        this.d = d;
    }
    public void run(){
        try {
            while(true) {
                sleep(1000);
                d.controlla();
            }
        } catch (InterruptedException ie) {
            System.out.println("Sono stato interrotto");
        }
    }
}