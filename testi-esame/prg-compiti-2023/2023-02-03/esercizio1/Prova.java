package esercizio1;

import java.util.*;

/**
 *
 * @author aless_irdbul5
 */
public class Prova {
    private static List<Integer> creaLista() {
        List<Integer> l = new ArrayList<Integer>();
        for(int i=0; i<1000000; i++) 
            l.add((int)(Math.random()*10000));
        return l;
    }
    public static void main(String[] args) throws Exception {
        FutureExec ef = new FutureExec();
        SortingTask c1 = new SortingTask(creaLista(), System.currentTimeMillis() + 10000, 1000);
        ef.add(c1);
        System.out.println("First SortingTask added");
        SortingTask c2 = new SortingTask(creaLista(), System.currentTimeMillis() + 10010, 100);
        ef.add(c2);
        System.out.println("Second SortingTask added");
        
        
        String r1 = c1.getResult();
        System.out.println("File path: " + r1);

        String r2 = c2.getResult();
        System.out.println("File path: " + r2);

    }
}
