import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class ConcurrentModificationExceptionDemo {
    private List<Integer> list = new ArrayList<>();

    public ConcurrentModificationExceptionDemo() {
        IntStream
                .generate(()-> new Random().nextInt(100))
                .limit(30).forEach(i-> list.add(i));
    }

    public void readThread(){
        Runnable readRunnable = ()-> {
            Iterator<Integer> iterator = list.iterator();
            while (iterator.hasNext()) {
                Integer number = iterator.next();
                System.out.println(number);
            }
        };
        new Thread(readRunnable).start();
    }

    public void writeThread(){
        Runnable writeRunnable = ()-> {
            IntStream.range(1000, 1100).forEach(i-> list.add(i));
        };
        new Thread(writeRunnable).start();
    }

    public static void main(String[] args) {
        ConcurrentModificationExceptionDemo concurrentModificationExceptionDemo
                = new ConcurrentModificationExceptionDemo();
        concurrentModificationExceptionDemo.readThread();
        concurrentModificationExceptionDemo.writeThread();
    }
}
