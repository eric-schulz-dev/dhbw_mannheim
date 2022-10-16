package Aufgabe11;

import java.util.concurrent.Semaphore;

public class PhilosophAdrian extends Thread{
    private static Semaphore[] gabel = new Semaphore[5];
    private int id;

    public PhilosophAdrian (int id){
        this.id = id;
    }

    public void run(){

    }
}
