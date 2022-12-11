import java.util.concurrent.Semaphore;

public class AlternateEvenOdd {

    private Semaphore semEven = new Semaphore(1);
    private Semaphore semOdd = new Semaphore(0);

    void printEven(){
        for (int i = 0; i <= 21; i += 2) {
            try {
                semEven.acquire();
            }
            catch (Exception e) {
            }
            System.out.println(Thread.currentThread().getName() + " " +  i);
            semOdd.release();
        }
    }

    void printOdd(){
        for (int i = 1; i <= 21; i += 2) {
            try {
                semOdd.acquire();
            }
            catch (Exception e) {
            }
            System.out.println(Thread.currentThread().getName() + " " +  i);
            semEven.release();
        }
    }

    public static void main(String[] args) {
        AlternateEvenOdd alternateEvenOdd = new AlternateEvenOdd();

        new Thread ( ()-> {
            alternateEvenOdd.printEven();
        }).start();

        new Thread ( ()->{
            alternateEvenOdd.printOdd();
        }).start();
    }
}
