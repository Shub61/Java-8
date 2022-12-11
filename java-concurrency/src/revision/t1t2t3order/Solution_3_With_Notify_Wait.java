package revision.t1t2t3order;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * Qs : T2 finishes after T1 and T3 finishes after T2.
 * Idea : Use a Shared Variable to Increase Count and match that count in T1 , T2 and T3
 * Have 3 Tasks WITH Synchronized Block.
 * In task 1 for t1 : finish the task, increment the count and then notify All.
 * Now in t2, wait until count != 2, then finish the task, increment count and notify All
 * Now in t3, wait until count != 3, then finish the task.
 */
public class Solution_3_With_Notify_Wait {

    public Solution_3_With_Notify_Wait() {
        this.count = 1;
    }

    int count;

    Runnable task1 = () -> {
        synchronized (this) {
            IntStream.range(1, 100).forEach(i -> System.out.println("First->" + i));
            count++;
            this.notifyAll();
        }
    };
    Runnable task2 = () -> {
        synchronized (this) {
            while (count != 2) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            IntStream.range(1, 100).forEach(i -> System.out.println("Second->" + i));
            count++;
            this.notifyAll();
        }
    };
    Runnable task3 = () -> {
        synchronized (this) {
            while (count != 3) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        IntStream.range(1, 100).forEach(i -> System.out.println("Third->" + i));
        count++;
    };

    public static void main(String[] args) throws InterruptedException {
        Solution_3_With_Notify_Wait solution_3_with_notify_wait = new Solution_3_With_Notify_Wait();
        Thread t1 = new Thread(solution_3_with_notify_wait.task1,"t1");
        Thread t2 = new Thread(solution_3_with_notify_wait.task2,"t2");
        Thread t3 = new Thread(solution_3_with_notify_wait.task3, "t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
