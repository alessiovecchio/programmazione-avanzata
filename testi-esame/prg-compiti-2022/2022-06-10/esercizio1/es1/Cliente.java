package es1;

public class Cliente extends Thread {

  private Traghetto t;
  
  public Cliente(Traghetto t) {
    this.t = t;
  }

  public void run(){
    try {
      t.visualizza(0);
      if(t.seleziona(0, 3)) System.out.println("Posto selezionato");
      else System.out.println("Posto occupato");
      t.visualizza(0);
      if(t.seleziona(0, 5)) System.out.println("Posto selezionato");
      else System.out.println("Posto occupato");
      t.visualizza(0);
      t.paga(0, 3);
      Thread.sleep(6000);
      t.visualizza(0);
    } catch (InterruptedException ie) {
      System.out.println("Sono stato interrotto...");
    }
  }
}
