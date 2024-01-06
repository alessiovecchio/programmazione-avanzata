public class Prova {
	public static void main(String[] args){
		Campeggio r = new Campeggio(20);
		for(int i=0; i<5; i++)
			new Cliente(r).start();
	}
}
