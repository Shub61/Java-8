package custom_executor_service;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class CustomThreadPoolExecutor {
    /**
     *  need to have list of worker threads
     *  need to have a queue :: BlockingQueue to store the tasks
     */
    private final Queue<Runnable> tasksQueue;
    private final Thread [] workerThreads;

    public CustomThreadPoolExecutor(int numberOfWorkers, int capacityOfTasks) {
        workerThreads = new Thread[numberOfWorkers];
        tasksQueue = new LinkedBlockingDeque<>(capacityOfTasks);
        AtomicInteger count = new AtomicInteger(1);
        Arrays.stream(workerThreads).forEach(t -> {
            t = new Worker("Worker Thread from Pool -> " + count.getAndIncrement());
            t.start();
        });
    }

    public void addTask(Runnable r) {
        tasksQueue.offer(r);
    }

    class Worker extends Thread{
        public Worker(String name) {
            super(name);
        }
        /**
         * It has to pick a task from taskQueue and execute t.
         */
        @Override
        public void run(){
            while (true) {
                try {
                    if(!tasksQueue.isEmpty()){
                        tasksQueue.poll().run();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    Runnable taskOne = ()-> IntStream.range(1, 20).filter(i-> i % 2 == 0).forEach(i-> {
        System.out.println(Thread.currentThread().getName() + " :: " + (i));
    });

    Runnable taskTwo = ()-> IntStream.range(1, 20).filter(i-> i % 2 != 0).forEach(i-> {
        System.out.println(Thread.currentThread().getName() + " :: " + (i));
    });
    Runnable taskThree = ()-> IntStream.range(1, 20).forEach(i-> {
        System.out.println(Thread.currentThread().getName() + " :: " + (i * i));
    });

    Runnable taskFour = ()-> IntStream.range(1, 20).forEach(i-> {
        System.out.println(Thread.currentThread().getName() + " :: " + (i * i * i));
    });

    Runnable taskFive = ()-> IntStream.range(1, 20).forEach(i-> {
        System.out.println(Thread.currentThread().getName() + " :: " + (i * i * i * i));
    });

    public static void main(String[] args) {
        CustomThreadPoolExecutor customThreadPoolExecutor = new CustomThreadPoolExecutor(5, 10);
        customThreadPoolExecutor.addTask(customThreadPoolExecutor.taskOne);
        customThreadPoolExecutor.addTask(customThreadPoolExecutor.taskTwo);
        customThreadPoolExecutor.addTask(customThreadPoolExecutor.taskThree);
        customThreadPoolExecutor.addTask(customThreadPoolExecutor.taskFour);
        customThreadPoolExecutor.addTask(customThreadPoolExecutor.taskFive);
    }
}
