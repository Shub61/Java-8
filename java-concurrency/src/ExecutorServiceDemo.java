import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ExecutorServiceDemo {
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        Runnable task = ()-> IntStream.range(1, 2).forEach(i->{
            System.out.println(Thread.currentThread().getName() + "::" + i);
        });
        IntStream.range(1, 100).forEach(i-> {
            executorService.execute(task);
        });
        // In General ExecutorService/Pool will remain active until we call shutdown()/shutdownNow().
        // Internally newFixedThreadPool uses BlockingQueue for TASKS.
        executorService.shutdown();
    }
}
