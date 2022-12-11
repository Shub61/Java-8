package frame_code;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;
import java.util.stream.IntStream;

//Event ID: EPC-NCET-QqJ9-MZQ2W
public class AsyncUtil {
    private static int count = 1;

    public static <TResult> CompletableFuture<TResult> runAsync(ExecutorService pool, Supplier<TResult> supplier){
        return CompletableFuture.supplyAsync(supplier, pool);
    }
}
