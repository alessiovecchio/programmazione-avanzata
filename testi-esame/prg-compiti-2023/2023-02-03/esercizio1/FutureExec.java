package esercizio1;

import java.util.PriorityQueue;

public class FutureExec implements Runnable {

    private final Thread t;
    private final PriorityQueue<SortingTask> pq;

    public FutureExec() {
        pq = new PriorityQueue<SortingTask>();
        t = new Thread(this);
        t.start();
    }

    public synchronized void add(SortingTask c) {
        pq.add(c);
        notify();
    }

    @Override
    public void run() {
        try {
            while (true) {
                SortingTask c = waitNextSortingTask();
                long start = c.getScheduledTime();
                long tol = c.getAllowedDelay();
                if (start + tol > System.currentTimeMillis()) {
                    try {
                        c.execute();
                    }catch(Exception ex) {
                        c.setException(ex);
                    }
                } else {
                    c.setException(new TooMuchDelay());
                }
            }
        } catch (InterruptedException i) { 
            System.out.println("Quitting...");
        }
    }

    private synchronized SortingTask waitNextSortingTask() throws InterruptedException {
        while (pq.isEmpty() || System.currentTimeMillis() < pq.peek().getScheduledTime()) {
            long attesa = pq.isEmpty() ? 0 :  pq.peek().getScheduledTime() - System.currentTimeMillis();
            wait(attesa);
        }
        return pq.poll();
    }
}