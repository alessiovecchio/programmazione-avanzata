public class Prova {
    public static void main(String[] args) {
        Sala s = new Sala(5, 2);
        for(int i=0; i<10; i++)
            new Paziente(s, (int)(Math.random()*5)).start();
    }
}