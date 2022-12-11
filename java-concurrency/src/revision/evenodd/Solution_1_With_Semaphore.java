package revision.evenodd;

import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class Solution_1_With_Semaphore {
    Semaphore even = new Semaphore(0);
    Semaphore odd = new Semaphore(1);
    Runnable evenTask = () -> {
        IntStream.range(1, 100).filter(i -> i % 2 == 0).forEach(i -> {
            try {
                even.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            odd.release();
        });
    };
    Runnable oddTask = () -> {
        IntStream.range(1, 100).filter(i -> i % 2 != 0).forEach(i -> {
            try {
                odd.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            even.release();
        });
    };

    public static void main(String[] args) {
        Solution_1_With_Semaphore solution_1_with_semaphore = new Solution_1_With_Semaphore();
        Thread e = new Thread(solution_1_with_semaphore.evenTask);
        Thread o = new Thread(solution_1_with_semaphore.oddTask);
        e.start();
        o.start();
    }
}
