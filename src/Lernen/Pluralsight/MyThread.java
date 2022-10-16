package Lernen.Pluralsight;

import java.util.concurrent.Semaphore;

public class MyThread {


    public static void main (String[] args){

        Semaphore mySem = new Semaphore(2);
        System.out.print("current permits: " + mySem.availablePermits());


    }
}
