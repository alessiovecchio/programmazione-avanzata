package esercizio1;

import java.io.*;

public class Prova {
    public static void main(String[] args) {
        try {
            ImageProcessor ip = new ConvertToGrayImageProcessor("img.txt");

            ip.process(2);
            Image i = ip.getImage();
            System.out.println(i.getRows());
            System.out.println(i.getColumns());
            System.out.println(i.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}