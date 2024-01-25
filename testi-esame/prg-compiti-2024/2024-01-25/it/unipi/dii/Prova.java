package it.unipi.dii;

public class Prova {
	public static void main(String[] args) {
		GestoreMemoria gm = new GestoreMemoria(100, 20);
		for(int i=0; i<12; i++)
			new Processo(gm, i%2==0).start();
	}
}
