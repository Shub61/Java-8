import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerLockCondition {

        ProducerConsumerLockCondition producerConsumerLockCondition = new ProducerConsumerLockCondition();


        public static void main(String[] args) {
            MyBlockingQueue myBlockingQueue = new MyBlockingQueue(5);
            Runnable producer = ()-> {
                while (true){
                    myBlockingQueue.put(1);
                }
            };

            Runnable consumer = ()-> {
                while (true){
                    myBlockingQueue.get();
                }
            };
            new Thread(producer).start();
            new Thread(producer).start();

            new Thread(consumer).start();
            new Thread(consumer).start();
        }

}

class MyBlockingQueue {
    private Queue myBlockingQueue;
    private int capacity;

    public MyBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.myBlockingQueue = new LinkedList();
    }

    ReentrantLock lock = new ReentrantLock(true);
    Condition notEmpty = lock.newCondition();
    Condition notFull = lock.newCondition();

    public void put(int item){
        lock.lock();
        if(myBlockingQueue.size() == capacity){
            try {
                notFull.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        myBlockingQueue.add(item);
        System.out.println("Added By " + Thread.currentThread().getName() + " " + 1);
        notEmpty.signalAll();
        lock.unlock();
    }

    public void get(){
        lock.lock();
        if(myBlockingQueue.size() == 0){ // Replace it with while condition to make sure
                                            // thread will only comes out when queue i nt empty
            try {
                notEmpty.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Integer i  = (Integer) myBlockingQueue.peek();
        System.out.println("Taken By " + Thread.currentThread().getName() + " " + i);
        notFull.signalAll();
        lock.unlock();
    }
}
