public class Paziente extends Thread {

    private int codice;
    private Sala sala;

    public Paziente(Sala sala, int codice) {
        this.sala = sala;
        this.codice = codice;
    }

    public void run () {
        try {
            sala.entrata(codice);
            sleep((int)(Math.random()*1000));
            sala.uscita();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
       
}
