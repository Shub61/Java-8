import java.util.concurrent.atomic.AtomicBoolean;

public class VolatileAtomicDemo extends Thread{
    boolean flag = true;
    @Override
    public void run() {
        while (flag){
            System.out.println(Thread.currentThread().getName() + ":: Task 2");
        }
    }
    public static void main(String[] args) throws InterruptedException {
        VolatileAtomicDemo volatileAtomicDemo = new VolatileAtomicDemo();
        volatileAtomicDemo.start();
        Thread.sleep(1000);
        volatileAtomicDemo.flag = false;
        System.out.println(Thread.currentThread().getName() + ":: Main Task");
    }
}
