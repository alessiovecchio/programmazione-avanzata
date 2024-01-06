package esercizio1;

import java.io.*;

public class Prova {
  public static void main(String[] args){
    try {
      // Crea un Logger in grado di conterenre al piu' 5 messaggi
      Logger m = new Logger("logfile.txt", 5);
      // Crea un Produttore che inserisce messaggi con numero d'ordine da 0 a 10
      new Produttore(0, 10, m).start();
      // Crea un Produttore che inserisce messaggi con numero d'ordine da 11 a 100
      new Produttore(11, 100, m).start();
      // Crea un Produttore che inserisce messaggi con numero d'ordine da 101 a 120
      new Produttore(101, 120, m).start();    
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
}
