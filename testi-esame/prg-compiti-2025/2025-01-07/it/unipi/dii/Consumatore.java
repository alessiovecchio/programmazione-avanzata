package it.unipi.dii;

public class Consumatore extends Thread {

    private final Deposito d;
    private final int k;

    public Consumatore(Deposito d, int k) {
        this.d = d;
        this.k = k;
    }
    public void run(){
        try {
            while(true) {
                Messaggio[] ll = d.estrai(k);
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " sono stato interrotto");
        }
    }
}