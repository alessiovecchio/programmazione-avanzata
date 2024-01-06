package esercizio1;

import java.io.*;
import java.util.Scanner;

public abstract class ImageProcessor {

    /* Numero di flussi che devono ancora terminare */
    private int toComplete;
    /* Numero di flussi da usare */
    private int n;
    /* The image */
    private Image image;

    public ImageProcessor(String filename) throws FileNotFoundException {
        load(filename);
    }

    public synchronized void process(int n) throws InterruptedException, IOException {
      // Attiva n flussi
      for(int j=0; j<n; j++)
        new Worker(image, j).start();
      this.n = n;
      toComplete = n;
    }

    public synchronized Image getImage() throws InterruptedException {
      // Attende che tutti i flussi abbiano finito
      waitForComplete();
      
      System.out.println("Fatto");   
      return image;
    }

    private void load(String filename) throws FileNotFoundException {
      Scanner s = new Scanner(new File(filename));
      int r = s.nextInt();
      int c = s.nextInt();
      image = new Image(r, c);

      for(int x=0; x<r; x++)
        for(int y=0; y<c; y++)
          image.red[x][y] = s.nextByte();
      
      for(int x=0; x<r; x++)
        for(int y=0; y<c; y++)
          image.green[x][y] = s.nextByte();
          
      for(int x=0; x<r; x++)
        for(int y=0; y<c; y++)
          image.blue[x][y] = s.nextByte();    
    }

    // Attende che tutti i flussi abbiano finito
    private void waitForComplete() throws InterruptedException {
      while(toComplete > 0)
        wait();
    }

    // Chiamato alla fine di ogni flusso
    private synchronized void completed() {
      toComplete--;
      if(toComplete == 0)
          notify();
    }

    /* Implementato dalle sottoclassi, defiisce il tipo di elaborazione da compiere */
    protected abstract void processRow(Image i, int j);

    /* Flusso di esecuzione */
    class Worker extends Thread {

      private Image i;
      private int index;

      Worker(Image i, int index) {
        this.i = i;
        this.index = index;
      }

      public void run() {
        int r = i.getRows();
        for(int j=index; j<r; j+=n)
          processRow(i, j);
        completed();
      }
    }
}
