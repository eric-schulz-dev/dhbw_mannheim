package Semester3;// (a)Schreibe ein Programm, welches ein Array der Größe 2^21
// mit IntegerWerten > 0 füllt. Davon soll die Gesamtsumme
// gebildet und ausgegeben werden. Die Zeit Dafür soll
// gemessen werden (ms).
// (b)Das soll mit 2,3,8,16,32,64 und 128 Worker Threads wiederholen.
//  Diese teilen das zusammenzählen es arrays auf.


import java.util.Arrays;

public class MyThread2 extends Thread{

    private int id;
    private int[] array;
    private long threadRuntime;
    public MyThread2 (int id, int[] array) {
        this.id = id;
        this.array = array;
        this.threadRuntime = 0;
    }

    public void run(){

        threadRuntime = calculateAndMeasureSum(array);
        System.out.println("thread id=" + id + ", runtime: " + threadRuntime +"ms");
    }

    public long getThreadRuntime() {
        return threadRuntime;
    }

    public static void main(String[] args) {
        int[] numbersArray = new int[2097152];
        long[] runtimeArray;

        fillArray(numbersArray);
        calculateWithThreads(1, numbersArray);
        calculateWithThreads(2, numbersArray);
        calculateWithThreads(4, numbersArray);
        calculateWithThreads(8, numbersArray);
        calculateWithThreads(16, numbersArray);
        calculateWithThreads(32, numbersArray);



    }

    private static void printOverallRuntime(long[] runtimeArray) {
        long overallRuntime = 0;
        for (int i=0; i<runtimeArray.length; i++){
            overallRuntime = overallRuntime+runtimeArray[i];
        }
        System.out.println("Overall Runtime was: " + overallRuntime + "\n");
    }

    private static long[] calculateWithThreads(int numberOfThreads, int[] numbersArray) {
        //initialize
        long[] timeArray = new long[numberOfThreads];
        Thread[] threadArray = new MyThread2[numberOfThreads];
        int calcRange = numbersArray.length / numberOfThreads;
        int[] calcArray;

        //declare threadArrray and start threads
        for (int tid=0; tid<numberOfThreads; tid++ ){
                calcArray = Arrays.copyOfRange(numbersArray, (calcRange*numberOfThreads), (calcRange*(numberOfThreads+1)));
                threadArray[tid] = new MyThread2(tid, calcArray);
                threadArray[tid].start();
        }
        //Wait for threads ending
        for(int tid=0; tid<numberOfThreads; tid++) {
            try {
                threadArray[tid].join();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(numberOfThreads + " threads stopped");

        return timeArray;


    }

    private static int[] fillArray(int[] array) {
        for (int i=0; i<array.length; i++){
            array[i] = i*i;
        }
        return array;
    }

    private static long calculateAndMeasureSum(int[] array){
        int sum = 0;
        long startTime;
        long endTime;

        startTime = System.currentTimeMillis();
        
        for(int i=0; i<array.length; i++){
            sum = sum + array[i];
        }
        
        endTime = System.currentTimeMillis();
        return endTime - startTime;
    }


}


