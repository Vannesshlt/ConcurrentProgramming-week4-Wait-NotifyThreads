/**
 * Created by VannessTan on 19/04/2016.
 */

public class GetThread implements Runnable {

    int[] queue;
    int startPointer, endPointer;
    boolean flag = true;

    public GetThread(int[] queue, int startPointer, int endPointer, boolean flag) {

        this.queue = queue;
        this.startPointer = startPointer;
        this.endPointer = endPointer;
        this.flag = flag;
    }

    public void run() {

        while (true){

            if ((flag && queue[startPointer] != 0)){
                synchronized(queue){
                    System.out.printf("%2d is removed.\n", queue[startPointer]);
                    queue[startPointer] = 0;
                    startPointer++;
                    queue.notify();
                }
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (!flag || queue[startPointer] == 0){
                synchronized(queue){
                    try {
                        System.out.println("** Waiting for thread 1... **");

                        while(queue[startPointer] == 0){
                            queue.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            if(startPointer == 5) {startPointer = 0;}
        }
    }
}






