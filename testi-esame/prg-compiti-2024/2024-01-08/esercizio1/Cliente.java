package esercizio1;

public class Cliente extends Thread {

    private boolean vip;
    private Negozio negozio;

    public Cliente(Negozio negozio, boolean vip) {
        setName(getName() + (vip ? "(VIP)" : ""));
        this.vip = vip;
        this.negozio = negozio;
    }

    public void run () {
        try {
            negozio.entrata(vip);
            sleep((int)(Math.random()*1000)+1000);
            negozio.uscita(vip);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
       
}
