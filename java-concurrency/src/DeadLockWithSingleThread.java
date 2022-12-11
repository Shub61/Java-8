public class DeadLockWithSingleThread {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Start");
        Thread.currentThread().join();
        System.out.println("End");
    }
}
