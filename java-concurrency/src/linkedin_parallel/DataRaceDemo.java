package linkedin_parallel;

import java.util.stream.IntStream;
//Event ID: EPC-NCET-QqJ9-MZQ2W
public class DataRaceDemo {
    private static int count = 1;

    public static void main(String[] args) throws InterruptedException {
        Runnable r1 = ()-> {
            IntStream.range(1, 5).forEach(i-> count++);
        };
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r1);
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println("Final count = " + count);
    }
}
