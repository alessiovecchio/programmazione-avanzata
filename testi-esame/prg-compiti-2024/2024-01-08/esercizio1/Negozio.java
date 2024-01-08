package esercizio1; 

public class Negozio {
	/* Numero massimo di clienti */
	private final int N;
	/* Numero massimo di clienti non-vip */
	private final int M;
	/* Numero di visitatori attualmnte in negozio */
	private int visitatori;	
	/* Numero di visitatori normali attualmente in negozio */
	private int normali;
	/* Numero di VIP che attendono di entrare nel negozio */
	private int vipInAttesa;
	/* E' stato raggiunto il numero massimo di clienti e il negozioe' chiuso */
	private boolean chiuso;

	/**
	 * Crea un negozio
	 * @param N numero massimo di clienti
	 * @param M numero massimo di clienti normali
	 */
	public Negozio(int N, int M) {
		if (N <= 0) {
			throw new IllegalArgumentException("N non valido");
		}
		if (M < 0 || M >= N) {
			throw new IllegalArgumentException("M non valido");
		}
		this.N = N;
		this.M = M;
	}

	/**
	 * Un nuovo cliente entra nel negozio. Il metodo puo' essere bloccante.
	 * 
	 * @param vip indica se il cliente e' vip o meno
	 * @throws InterruptedException
	 */
	public synchronized void entrata(boolean vip) throws InterruptedException {
		if(vip)
			vipInAttesa++;
		while (chiuso || (!vip && normali >= M) || (!vip && vipInAttesa>0)) {
			System.out.println("Cliente " + Thread.currentThread().getName() 
			                              + " in attesa");
			wait();
		}
		System.out.println("Cliente " + Thread.currentThread().getName()  + " entrato");
		visitatori++;
		if(vip)
			vipInAttesa--;
		else
			normali++;
		chiuso = (visitatori == N);
		if(chiuso) {
			System.out.println("Negozio chiuso");
		} 
	}

	/**
	 * Il cliente esce dal negozio. 
	 * 
	 * @param vip indica se il cliente e' vip o meno
	 */
	public synchronized void uscita(boolean vip) {
		visitatori--;
		if(!vip) 
			normali--;
		System.out.println("Cliente " + Thread.currentThread().getName() + " uscito");

		if (chiuso && visitatori == 0) {
			chiuso = false;
			System.out.println("Negozio riaperto");
		}
		notifyAll();
	}
}		
