/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esercizio1;

import java.util.Collections;
import java.io.*;
import java.util.*;

/**
 *
 */
public class SortingTask implements Comparable<SortingTask> {

    private final int allowedDelay;
    private boolean completed;
    private String result;
    private final long scheduledTime;
    private Exception ex;
    private final List<Integer> list;

    public SortingTask(List<Integer> list, long scheduledTime, int allowedDelay) {
        this.list = list;
        this.scheduledTime = scheduledTime;
        this.allowedDelay = allowedDelay;
    }

    @Override
    public int compareTo(SortingTask st) {
            if (getScheduledTime() < st.getScheduledTime()) {
                return -1;
            }
            if (getScheduledTime() > st.getScheduledTime()) {
                return 1;
            }
            return 0;
        }

    public synchronized void execute() {
        Collections.sort(list);
        File f = new File(scheduledTime + ".txt");
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(f)))
        { 
            wr.append(list.toString());
            result = f.getAbsolutePath();
            completed = true;
            notifyAll();
        } catch (Exception e) {
            setException(e);
        }
    }

    public synchronized String getResult() throws Exception {
        while (!completed) {
            wait();
        }
        if(ex != null)
            throw ex;
        return result;    
    }

    public synchronized boolean isComplete() {
        return completed;
    }

    public int getAllowedDelay() {
        return allowedDelay;
    }

     
    public long getScheduledTime() {
        return scheduledTime;
    }

    public synchronized void setException(Exception e) {
        completed = true;
        ex = e;
        notifyAll();
    }
    
    public String toString(){
        return "[" + scheduledTime + ", " + allowedDelay + "]";
    }
}
