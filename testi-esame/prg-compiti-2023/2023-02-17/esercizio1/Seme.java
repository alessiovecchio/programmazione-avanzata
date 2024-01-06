package esercizio1;

public enum Seme {

    CUORI, QUADRI, FIORI, PICCHE;
    
    public String toString(){
        switch(this) {
            case CUORI: return "C";
            case QUADRI: return "Q";
            case PICCHE: return "P";
            case FIORI: return "F";
        }
        return null;
    }
};
