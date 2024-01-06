package es1;

import java.util.*;

public class Traghetto {
 
  /* Numero di posti in ogni piano */ 
  private static int DIM_PIANO = 60;
  /* Tempo dopo il quale un posto selezionato torna libero se non viene eseguito il pagamento */
  private static long TIMEOUT = 5000;

  // Numero di piani del traghetto
  private int npiani;  
  // Thread ausilario che si preoccupa di rilasciare i posti
  private Worker worker;
  // Stato dei posti del traghetto, ogni riga un piano
  Stato[][] posto;

  /**
   * Crea un traghetto con npiani piani
   */
  public Traghetto(int npiani) {
    this.npiani = npiani;
    this.posto = new Stato[npiani][DIM_PIANO];
    for(int i=0; i<npiani; i++) 
      for(int j=0; j<DIM_PIANO; j++)
        posto[i][j] = Stato.libero;
    this.worker = new Worker(this);
    worker.start();
  }

  /**
   * Visualizza lo stato del piano n
   */ 
  public synchronized void visualizza(int n) {
    for(int i=0; i<DIM_PIANO; i++) {
      System.out.print(posto[n][i]);
      if((i+1) % 6 == 0) System.out.println();
    }
  }
  
  /**
   * Prenota il posto p al piano n 
   */
  public synchronized boolean seleziona(int n, int p) {
    System.out.println("Selezione posto " + p + " del piano " + n);
    boolean res = false;
    if(posto[n][p] == Stato.libero) {
        posto[n][p] = Stato.selezionato;
        long tempo = System.currentTimeMillis();
        // chiede al worker di controllare il posto in questione
        // allo scadere del timeout
        worker.controlla(n, p, tempo + TIMEOUT); 
        res = true;
    }
    return res;
  }

  /**
   * Completa l'acquisto per il posto p del piano n
   */
  public synchronized boolean paga(int n, int p) {
    System.out.println("Pagamento per posto " + p + " del piano " + n);
    if(posto[n][p] == Stato.selezionato) {
      posto[n][p] = Stato.prenotato;
      return true;
    }
    return false;
  }

}
