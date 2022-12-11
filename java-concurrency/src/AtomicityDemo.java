public class AtomicityDemo extends Thread{
    int value  = 1;
    @Override
    public void run() {
        value++;
        System.out.println(Thread.currentThread().getName() + ":: Normal Thread " + value);
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicityDemo atomicityDemo = new AtomicityDemo();
        atomicityDemo.start();
        //Thread.sleep(1000);
        atomicityDemo.value ++;
        System.out.println(Thread.currentThread().getName() + ":: Main Task " + atomicityDemo.value);
    }
}
