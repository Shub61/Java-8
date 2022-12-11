package revision.t1t2t3order;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * Qs : T2 finishes after T1 and T3 finishes after T2.
 * Idea :
 *      Have 2 separate Latches.
 *      One for t1 and one for t2. With Count = 1 only
 *      Now in t1, countDown once t1 does it's task.
 *      Now in t2, await on latch_1 (which is T1), then T2 finishes and CountDown latch_2.
 *      Now in t3, await on latch_2 (which is T2)
 */
public class Solution_2_With_CountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch_1 = new CountDownLatch(1);
        CountDownLatch countDownLatch_2 = new CountDownLatch(1);
        Thread t1 = new Thread(()-> {
            IntStream.range(1, 100).forEach(i->System.out.println ("First->"  + i));
            countDownLatch_1.countDown();
        },"t1");

        Thread t2 = new Thread(()-> {
            try {
                countDownLatch_1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            IntStream.range(1, 100).forEach(i->System.out.println ("Second->"  + i));
            countDownLatch_2.countDown();
        }, "t2");

        Thread t3 = new Thread(()-> {
            try {
                countDownLatch_2.await();
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
