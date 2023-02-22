package Uebung1;

public class Philosopher extends Thread{
    private int id;
    private Uebung1.MyMonitor myMonitor;

    //Constructor
    public Philosopher(int id, Uebung1.MyMonitor m){
        this.id = id;
        this.myMonitor = m;
    }

    public void run(){
        for(int i = 0; i<5; i++){
            try{
                Thread.sleep(2000);

            }catch(Exception e){}
            myMonitor.startEating(id);
            try{
                Thread.sleep(4000);
            }catch(Exception e){}
            myMonitor.stopEating(id);
        }
    }

    public static void main (String[]args){
        int max = 5; //eaters? // Hier hatte Pagnia 8!
        Uebung1.MyMonitor mainMyMonitor = new Uebung1.MyMonitor(max);
        Philosopher[] philosopher = new Philosopher[max];

        System.out.println("start meal");
        for(int i=0; i < max; i++){
            philosopher[i] = new Philosopher(i, mainMyMonitor);
            philosopher[i].start();
        }

        for(int i=0; i < max; i++){
            try{
                philosopher[i].join();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("Everything has stoppped");


    }
}
