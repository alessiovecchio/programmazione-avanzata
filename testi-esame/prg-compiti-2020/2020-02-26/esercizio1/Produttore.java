package esercizio1;

import java.io.*;

public class Produttore extends Thread {

  // Numero d'ordine di partenza
  private int o;
  // Numero d'ordine finale
  private int q;
  // Il Logger da usare
  private Logger m;

  public Produttore(int o, int q, Logger m) {
    this.m = m;
    this.o = o;
    this.q = q;
  }

  public void run() {
    try {
      for(int i=o; i<=q; i++) {
        m.log("\"" + Thread.currentThread().getName() + " messaggio numero " + i + "\"", i);
      }
    } catch (InterruptedException ie) {
      System.err.println(ie.getMessage());
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
    System.out.println(Thread.currentThread().getName() + " ho finito");
  }
}
