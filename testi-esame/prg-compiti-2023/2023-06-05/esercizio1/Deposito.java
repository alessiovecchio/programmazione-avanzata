package esercizio1;

import java.util.*;

public class Deposito {
    // I pezzi vengono memorizzati in questa List
    private List<Pezzo> l = new ArrayList<Pezzo>();
    // Numero massimo di pezzi che il deposito è in grado di contenere
    private final int dimensione;

    public Deposito(int dimensione) {
        this.dimensione = dimensione;
    }

    // Restituisce il numero di pezzi presenti nel deposito che hanno 
    // il tipo specificato
    private int contaTipo(Tipo t) {
        int conta = 0;
        for(Pezzo p: l)
            if(p.getTipo() == t)
                conta++;
        return conta;        
    }

    // Inserisce il pezzo p
    public synchronized void inserisci(Pezzo p) throws InterruptedException {
        // Mi blocco se
        // - il deposito è pieno oppure
        // - tutti i pezzi presenti hanno lo stesso tipo di p ed è rimasto un solo
        //   posto disponibile
        while(l.size() == dimensione || 
              contaTipo(p.getTipo()) == dimensione - 1) {
            System.out.println("mi blocco, non riesco a inserire "+ p);
            System.out.println("Deposito: " + this);
            wait();
        }
        l.add(p);
        // Eseguo una notifyAll() per risvegliare eventuali Consumatori bloccati 
        notifyAll();
    }

    // Restituisce true se manca uno dei due tipi (o se mancano entrambi)
    // restituisce false se entrambi i tipi di pezzi sono presenti
    private boolean tipoMancante() {
        return contaTipo(Tipo.X) == 0 || contaTipo(Tipo.Y) == 0;
    }

    // Estrae una coppia di pezzi X, Y
    public synchronized Pezzo[] estrai() throws InterruptedException{
        // Se uno dei due tipi è assente mi blocco
        while(tipoMancante()) {
            System.out.println("mi blocco, manca un tipo");
            System.out.println("Deposito: " + this);
            wait();
        }
        
        // Creo l'array per restituire il risultato
        Pezzo[] ar = new Pezzo[2];
        // Prendo il primo pezzo
        ar[0] = l.remove(0);
        // Cerco un pezzo che appartiene all'altro tipo
        for(int i=0; i<l.size(); i++) {
            if(l.get(i).getTipo() != ar[0].getTipo()) {
                ar[1] = l.remove(i);
                break;
            }
        }
        // Risveglio eventuali Produttori bloccati
        notifyAll();
        return ar;
    }

    // Rimuove i pezzi con qualita minore di 0.2
    public synchronized int controlla() {
        int conta = 0;
        for(int i=0; i<l.size(); i++) {
            if(l.get(i).getQualita() < 0.2) {
                l.remove(i);
                conta++;
                i--;
            }
        }
        notifyAll();
        return conta;
    }

    public String toString() {
        return l.toString();
    }

}