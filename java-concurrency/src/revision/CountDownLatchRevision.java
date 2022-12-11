package revision;

import javax.swing.tree.FixedHeightLayoutCache;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchRevision {
    CountDownLatch countDownLatch = new CountDownLatch(3);

    Runnable task = () -> {
        System.out.println("Working by Thread --> " + Thread.currentThread().getName());
        countDownLatch.countDown();
    };
    public static void main(String[] args) throws InterruptedException {
        CountDownLatchRevision countDownLatchRevision = new CountDownLatchRevision();
        Thread t1 = new Thread(countDownLatchRevision.task);
        Thread t2 = new Thread(countDownLatchRevision.task);
        Thread t3 = new Thread(countDownLatchRevision.task);
        t1.start();t2.start();t3.start();
        countDownLatchRevision.countDownLatch.await();
        System.out.println("Working by Thread --> " + Thread.currentThread().getName());
    }
}
