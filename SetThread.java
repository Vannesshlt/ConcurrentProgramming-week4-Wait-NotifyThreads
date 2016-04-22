/**
 * Created by VannessTan on 19/04/2016.
 */

import java.util.Random;

public class SetThread implements Runnable {

    int[] queue;
    int endPointer, startPointer;
    int counter = 0;
    boolean flag = true;

    Random rand = new Random();


    public SetThread(int[] queue, int startPointer, int endPointer, boolean flag) {

        this.queue = queue;
        this.startPointer = startPointer;
        this.endPointer = endPointer;
        this.flag = flag;
    }

    public void run() {

        while (true) {

            synchronized (queue) {

                if(queue[endPointer] == 0){
                    queue[endPointer] = rand.nextInt(100) + 1;
                    System.out.printf("%2d is added.\n", queue[endPointer]);
                    queue.notify();
                    endPointer++;
                    flag = true;

                }
                else{
                    System.out.println("** Waiting for thread 2... **");

                    while(queue[endPointer] != 0){
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            if(flag){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(endPointer == 5) {
                synchronized (queue) {
                    for (int i = 0; i < 5; i++) {
                        if (queue[i] != 0) {
                            counter++;
                        }
                    }

                    if (counter == 5) {
                        System.out.println("The queue is full now.");
                        flag = true;
                    }
                }

                try {
                    System.out.println("** Sleep for 5 seconds for every 5 elements **");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                endPointer = 0;
            }
        }
    }
}



