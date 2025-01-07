package it.unipi.dii;

public class Messaggio {
  
  private final boolean urgente;
  private final int id;
  private static int count = 0;

  public Messaggio(boolean urg) {
    urgente = urg;
    id = getCount();
  }

  /*
   * synchronized in modo che lo stesso id non venga
   * assegnato a due messaggi diversi contemporaneamente.
   */
  private static synchronized int getCount(){
    return count++;
  }

  public boolean isUrgente(){
    return urgente;
  }

  /**
   * Restituisce una rappresentazione testuale del messaggio.
   * Il formato e' il seguente:
   * <id U/N>
   * I messaggi sono dotati di un id in modo da 
   * poterli distinguere e  permettere la verifica del corretto
   * funzionamento del sistema.
   * 
   * @return una stringa che rappresenta il messaggio
   */
  public String toString(){
    return "<" + id + " " + (urgente ? "U" : "N") + ">";
  }
}
