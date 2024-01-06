package esercizio1;

public class Auto extends Thread {

	private Parcheggio p;
	
	public Auto(Parcheggio p) {
		this.p = p;
	}

	public void run(){
		try {
			p.attendi();	
		} catch (InterruptedException ie){
			System.out.println("Sono stato interrotto...");
		} catch (ParcheggioPienoException sp) {
			System.out.println("Eccezione..."); 
		}
	}
}
