package esercizio1;

public class Partita {

	private Carta[] mazzo;
	private int vendute;
	private Carta estratta;
	private int numGiocatori;
	private static int N = 40;

	public Partita(int n) {
		this.numGiocatori = n;
		mazzo = new Carta[N];
		for(int i=0; i<N; i++) {
			mazzo[i] = new Carta(i%10, Seme.values()[i/10]);
		}
	}

	public synchronized Carta compraCarta(int v, Seme s) throws CartaNonDisponibileException {
		if(v<0 || v>10)
			throw new IllegalArgumentException();

		int num = Carta.numeroOrdine(v, s);
		if(mazzo[num] == null)
			throw new CartaNonDisponibileException();

		Carta t = mazzo[num];	
		mazzo[num] = null;
		vendute++;
		return t;
	}

	public synchronized Carta[] carteDisponibili() {
		Carta[] tmp = new Carta[N-vendute];
		int conta = 0;
		for(Carta c: mazzo) {
			if(c != null)
				tmp[conta++] = c;
		}
		return tmp;
	}
	
	public synchronized boolean attendiEstrazione(Carta[] bb) throws InterruptedException {
		numGiocatori--;
		if(vendute == N && numGiocatori == 0) {
			estrai();
			notifyAll();
		}
		while(vendute != N || numGiocatori > 0)
			wait();

		for(int i=0; i<bb.length; i++)
			if(bb[i].equals(estratta))
				return true;
		return false;	
	}

	private void estrai() {
		int t = (int)(Math.random()*N);
		estratta = new Carta(t%10, Seme.values()[t/10]);
		System.out.println("Carta estratta: " + estratta);
	}
}
