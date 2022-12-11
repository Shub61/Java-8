package custom_executor_service;

import java.util.stream.IntStream;

public class Task {

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
}
