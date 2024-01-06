package es1;

public enum Seme {

    PICCHE, FIORI, QUADRI, CUORI;

    /* Restituisce una rappresentazione in formato stringa del 
       seme come una sola lettera */
    public String toString() {
        switch(this){
            case PICCHE: return "P";
            case FIORI: return "F";
            case QUADRI: return "Q";
            case CUORI: return "C";
            default: return null;
        }
    }
}