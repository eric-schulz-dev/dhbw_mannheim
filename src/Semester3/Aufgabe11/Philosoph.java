package Semester3.Aufgabe11;

import java.util.concurrent.Semaphore;

public class Philosoph extends Thread{

    private static int essensvorgaenge=0;
    private int id;
    public static Semaphore[] gabelArray = new Semaphore[5];
    private int gabelRechts = 0;
    private int gabelEigen;

    public Philosoph(int id, Semaphore[] gabelArray){
        this.id = id;
        this.gabelArray = gabelArray;
        if(id <4)
            gabelRechts = id+1;
        gabelEigen = id;
    }

    @Override
    public void run(){
        System.out.print("Philosoph " + id + " denkt gerade \n");
        for(int i=0; i<1000; i++){

            try {
                //p4 soll zuerst die rechte und dann die eigene gabel nehmen. P0-3 sollen umgekehrt.

                if(id==4){
                    gabelArray[gabelRechts].acquire();
                    gabelArray[gabelEigen].acquire();
                    System.out.println("Philosoph " + id + " isst gerade mit Gabeln: R:" + gabelRechts + " / E:" + gabelEigen +"\n");
                    sleep(2000);
                }
                else{
                    gabelArray[gabelEigen].acquire();
                    gabelArray[gabelRechts].acquire();
                    System.out.println("Philosoph " + id + " isst gerade mit Gabeln: R:" + gabelRechts + " / E:" + gabelEigen +"\n");
                    sleep(2000);
                }
                gabelArray[gabelRechts].release();
                gabelArray[gabelEigen].release();
                essensvorgaenge++;
                System.out.println(essensvorgaenge + " x gegesssen");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }




        }



    }

}
