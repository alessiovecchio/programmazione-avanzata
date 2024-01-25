package it.unipi.dii;

public class Blocco {

	private int numero;
	private static int cont;
	private boolean libera;

	public Blocco(){
		numero = cont++;
		libera = true;
	}

	public boolean isLibero() {
		return libera;
	}

	public void libera(){
		libera = true;
	}

	public void occupa() {
		libera = false;
	}

	public int getNumero() {
		return numero;
	}
}
