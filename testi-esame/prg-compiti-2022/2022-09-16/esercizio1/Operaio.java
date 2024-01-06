package esercizio1;
	
public class Operaio extends Thread {

	private Parcheggio p;
	private static final int PAUSA = 10000;
	
	public Operaio(Parcheggio p) {
		this.p = p;
	}

	public void run(){
		int contatore = 0;
		try {
			while(true) {
				p.lava();
				contatore++;
				if(contatore == 3) {
					sleep(PAUSA);
					contatore = 0;
 				}
			}
		} catch (InterruptedException ie) {
			System.out.println("Sono stato interrotto...");
		}
	}
}
