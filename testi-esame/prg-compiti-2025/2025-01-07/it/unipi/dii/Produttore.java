package it.unipi.dii;

public class Produttore extends Thread {
    
    private final float f;
    private final Deposito d;

    public Produttore(Deposito d, float f){
        this.d = d;
        this.f = f;
    }

    public void run(){
        try {
            while(true) {
                boolean p = Math.random() < f;
                Messaggio l = new Messaggio(p);
                d.inserisci(l);
            }    
        } catch (InterruptedException e) {
            System.out.println(getName() + " sono stato interrotto");
        }
    }
}