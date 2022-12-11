public class VolatileDemo extends Thread{
    volatile boolean flag = true;
    @Override
    public void run() {
        while (flag){
            System.out.println(Thread.currentThread().getName() + ":: Task 2");
        }
    }
    public static void main(String[] args) throws InterruptedException {
        VolatileDemo volatileDemo = new VolatileDemo();
        volatileDemo.start();
        Thread.sleep(1000);
        volatileDemo.flag = false;
        System.out.println(Thread.currentThread().getName() + ":: Main Task");
    }
}
