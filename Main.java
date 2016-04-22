/**
 * Created by VannessTan on 19/04/2016.
 */
public class Main {

    public static void main(String[] args){

        int[] queue = new int[5];
        int startPointer = 0, endPointer = 0;
        boolean flag = true;

        SetThread set = new SetThread(queue, startPointer, endPointer, flag);
        GetThread get = new GetThread(queue, startPointer, endPointer, flag);

        Thread t1 = new Thread(set);
        Thread t2 = new Thread(get);

        t1.start(); t2.start();

        try {
            t1.join();
            t2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
