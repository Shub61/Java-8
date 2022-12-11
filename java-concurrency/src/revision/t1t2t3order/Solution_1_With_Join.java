package revision.t1t2t3order;

import java.util.stream.IntStream;

/**
 * Qs : T2 finishes after T1 and T3 finishes after T2.
 * Idea :
 *      Have a t1 to do task1 , t2  for task 2 , t3 for task 3
 *      But in task 2 --> Make Sure we put T1.join() --> this ensures T1 will finish before T2
 *      But in task 3 --> Make Sure we put T2.join() --> this ensures T2 will finish before T3
 */
public class Solution_1_With_Join {
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(()-> IntStream.range(1, 100).forEach(i->System.out.println ("First->"  + i)),"t1");

        Thread t2 = new Thread(()-> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            IntStream.range(1, 100).forEach(i->System.out.println ("Second->"  + i));
        }, "t2");

        Thread t3 = new Thread(()-> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            IntStream.range(1, 100).forEach(i->System.out.println ("Third->"  + i));
        }, "t3");
        t1.start();
        t2.start();
        t3.start();
    }
}
