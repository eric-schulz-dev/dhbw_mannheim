package Uebung1;

public class MyMonitor {
    private boolean[] eating;
    private int max;

    public MyMonitor(int max){
        this.max = max;
        eating = new boolean[max];
        for(int i=0; i<max; i++){
            eating[i] = false;
        }
    }

    public synchronized void startEating (int i){ //start with eating
        while(eating[(i+max-1)%max] || eating[(i+1)%max] ){
            System.out.println("philosopher " + i + " is waiting");
            try{
                wait();
            }catch(Exception e){}
        }
        eating[i] = true;
        System.out.println("philosopher " + i + "is eating");
    }

    public synchronized void stopEating (int i){ //stop eating of all? one? let all know?
        eating[i] = false;
        System.out.println("philosopher " + i + " is thinking");
        notifyAll();
    }


}
