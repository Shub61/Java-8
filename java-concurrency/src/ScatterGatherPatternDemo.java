import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScatterGatherPatternDemo {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Integer> prices = new ArrayList<>();

        CountDownLatch countDownLatch = new CountDownLatch(3);
        executorService.submit(new UrlPriceTask("url1", "p1", prices, countDownLatch));
        executorService.submit(new UrlPriceTask("url2", "p2", prices, countDownLatch));
        executorService.submit(new UrlPriceTask("url3", "p3", prices, countDownLatch));

//        Thread.sleep(3000);
        // make it better use CountDownLatch
        countDownLatch.await();
        prices.stream().forEach(System.out::println);

        executorService.shutdown();

    }
}

class UrlPriceTask implements Runnable{
    String url;
    String productId;
    List<Integer> prices;
    CountDownLatch latch;
    public UrlPriceTask(String url, String productId, List<Integer> prices, CountDownLatch latch) {
        this.url = url;
        this.productId = productId;
        this.prices = prices;
        this.latch = latch;
    }

    @Override
    public void run() {
        prices.add(new Random().nextInt(100));
        latch.countDown();
    }
}
