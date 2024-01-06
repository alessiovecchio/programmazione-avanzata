package esercizio1;

import java.io.*;
import java.util.*;

public class Logger {

  // Crea una Map   Intero -> Stringa dove l'intero è il numero
  // d'ordine del messaggio e la stringa è il messaggio vero e proprio
  private Map<Integer, String> map = new HashMap<Integer, String>();
  // Posti liberi nel logger
  private int liberi;
  // Numero d'ordine del prossimo valore che può essere trasferito direttamente su file
  private int ordine;
  // Dove i valori vengono scritti
  private Writer ds;

  public Logger(String fn, int n) throws IOException {
    ds = new FileWriter(fn);
    liberi = n;
  }

  public synchronized void log(String r, int o) throws InterruptedException, IOException {
    System.out.println(Thread.currentThread().getName() + ": voglio loggare " + r + " " + o);
    while(ordine != o && liberi == 0) {
      // Non e' il mio turno e non c'e' spazio per bufferizzare il dato
      System.out.println(Thread.currentThread().getName() + 
                         ": mi blocco, il messaggio non può essere trasferito su file e non c'e' spazio");
      wait();
    }

    if(ordine == o) {
      // E' il turno di questo messaggio scrivo il dato nel file
      System.out.println(Thread.currentThread().getName() + ": il messaggio può essere trasferito su file");
      scrivi(r);
      while(true) {
        ordine++;
        // Guardo se nel buffer ci sono messaggi precedentemente memorizzati
        // e che possono adesso essere trasferiti su file
        String s = map.get(ordine);
        if(s != null) {
          // Questo messaggio puo' adesso essere trasferito su file
          // Scrivo i dati nel file
          scrivi(s);
          // Libero lo spazio
          liberi++;
          map.remove(ordine);
          System.out.println(Thread.currentThread().getName() + ": ho trasferito anche il messaggio numero " + 
                             ordine + " e adesso nel logger ci sono " + 
                             map.size() + " messaggi");

        } else {
          // Qualche thread bloccato puo' adesso trasferire nel buffer se necessario
          notifyAll();
          return;
        }
      }
    } else {
      // Non e' il turno di questo messaggio: memorizzo nel buffer e esco
      System.out.println(Thread.currentThread().getName() +": non posso trasferire su file ma c'è posto nel buffer");
      map.put(o, r);
      liberi--;
    }
  }

  /*
   * Scrive il valore r nel file (insieme al suo numero d'ordine)
   */
  private void scrivi(String r) throws IOException {
    ds.write(ordine + " " + r + "\n");
    ds.flush();
  }

}
