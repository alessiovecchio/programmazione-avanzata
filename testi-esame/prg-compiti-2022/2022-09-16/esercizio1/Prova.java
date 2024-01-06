package esercizio1;

public class Prova {
	public static void main(String[] args) {
		  Parcheggio p = new Parcheggio(5);
		  for(int i=0; i<10; i++)
		  	new Auto(p).start();
		  Operaio o = new Operaio(p);
		  o.start();
	}
}
