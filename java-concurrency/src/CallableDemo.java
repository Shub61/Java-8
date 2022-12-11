import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class CallableDemo {
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> task = ()-> new Random().nextInt(100);
        Future<Integer> future = executorService.submit(task);
        IntStream.range(1, 100).forEach(i-> {
            System.out.println("Hi" + i);
        });
        System.out.println(future.get());
        executorService.shutdown();
    }
}
