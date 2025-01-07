package it.unipi.dii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Deposito {

  // Dimensione di default
  private static final int DEFAULT_SIZE = 10;
  // Contenitore dei messaggi normali
  private final List<Messaggio> norm;
  // Contenitore dei messaggi urgenti
  private final List<Messaggio> urg;
  // Numero massimo di messaggi normali
  private final int maxNorm;
  // Numero massimo di messaggi urgenti
  private final int maxUrg;

  /**
   * Crea un nuovo deposito con le dimensioni specificate
   * @param n Numero massimo di messaggi normali
   * @param m Numero massimo di messaggi urgenti
   */ 
  public Deposito(int n, int m) {
    norm = new ArrayList<>(n);
    urg = new ArrayList<>(m);
    maxNorm = n;
    maxUrg = m;
  }

  /**
   * Crea un nuovo deposito con le dimensioni di default
   */
  public Deposito() {
    this(DEFAULT_SIZE, DEFAULT_SIZE);
  }
  
  /**
   * Inserisce un messaggio nel deposito
   * @param m Messaggio da inserire
   * @throws InterruptedException Se il thread viene interrotto
   */
  public synchronized void inserisci(Messaggio m) throws InterruptedException {
    if(m.isUrgente()) {
      while(urg.size() == maxUrg){
        System.out.println(Thread.currentThread().getName() + ": mi blocco, non riesco a inserire " + m);
        wait();
      }
      urg.add(m);
    } else {
      while(norm.size() == maxNorm){
        System.out.println(Thread.currentThread().getName() + ": mi blocco, non riesco a inserire " + m);
        wait();
      }
      norm.add(m);
    }
    System.out.println(Thread.currentThread().getName() + ": ho inserito " + m);
    notifyAll();
  }
   
  /**
   * Estrae messaggi dal deposito
   * @param q Numero di messaggi da estrarre
   * @return Array di messaggi estratti
   * @throws InterruptedException Se il thread viene interrotto
   * @throws IllegalArgumentException Se q e' maggiore del numero 
   *         massimo di messaggi normali che il deposito puo' contenere
   */
  public synchronized Messaggio[] estrai(int q) throws InterruptedException, IllegalArgumentException {
    if(q > maxNorm)
      throw new IllegalArgumentException();
    // Mi blocco se ci sono meno di q messaggi e nessuno e' urgente
    while(norm.size() < q && urg.isEmpty())
      wait();
    // Creo un array che conterra' il risultato
    q = q<(norm.size() + urg.size()) ? q : (norm.size() + urg.size());
    Messaggio[] res = new Messaggio[q];
    // Riempio l'array cominciando con i messaggi urgenti
    for(int i=0; i<q; i++) { 
      res[i] = urg.size() > 0 ? urg.remove(0): norm.remove(0);
    }
    System.out.print(Thread.currentThread().getName() + ": ho estratto " + q + " messaggi ");
    System.out.println(Arrays.toString(res));
    // Risveglio eventuali produttori bloccati
    notifyAll();
    return res;
  }
}
