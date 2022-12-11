package custom_executor_service;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadPool {
    /**
     *  need to have list of worker threads
     *  need to have a queue :: BlockingQueue to store the tasks
     */
    private final Queue<Runnable> tasksQueue;
    private final Thread [] workerThreads;

    public CustomThreadPool(int numberOfWorkers, int capacityOfTasks) {
        workerThreads = new Thread[numberOfWorkers];
        tasksQueue = new LinkedBlockingDeque<>(capacityOfTasks);
        AtomicInteger count = new AtomicInteger(1);
        Arrays.stream(workerThreads).forEach(t -> {
//            t = new CustomThreadPool.Worker("Worker Thread from Pool -> " + count.getAndIncrement());
//            t.start();
        });
    }
}
