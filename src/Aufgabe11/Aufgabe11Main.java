package Aufgabe11;

import java.util.concurrent.Semaphore;

public class Aufgabe11Main {

    public static void main (String[]args){
        Thread[] philosphenArray = new Thread[5];
        Semaphore[] gabelnArray = new Semaphore[5]; // Betriebsmittel

        //initialize philosophers
        for(int i=0; i<=4; i++){
            philosphenArray[i] = new Philosoph(i, gabelnArray);
        }
        //initialize
        for(int i=0; i<=4; i++){
            gabelnArray[i] = new Semaphore(1);
        }

        //start philosophers
        for(int i=0; i<=4; i++){
            philosphenArray[i].start();
        }

    }
}
