package Semester3;

public class MyThread1 extends Thread {
    //Das vorliegende Programm aus Folie31
    //Soll so geändert werden, dass nach Beendigung aller
    // Threads, eine Ausgabe erfolgt.
    private int id;
    public MyThread1 (int id){
        this.id = id;
    }//Konstruktor

    public void run() {
        try {
            Thread.sleep((int) (Math.random()* 1000 ));
        }catch (Exception e){}
        System.out.println("Hello_World_ ( ID= " + id + ")");
    }//run

    public static void main(String[]args){
        Thread[] threadArray = new Thread[10];
        for(int tid=1; tid<10; tid++){//Hier werden die threads gestartet
            threadArray[tid] = new MyThread1(tid);
            threadArray[tid].start();
        }
        // Nach start geht es direkt weiter. es wird auf nichts anderes gewartet
        for(int tid=1; tid<10; tid++){
            try {
                threadArray[tid].join();
                System.out.println("thread " + tid + " stop");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Alle fertig");
    }
}
