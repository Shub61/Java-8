import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FizzBuzz {
    ReentrantLock lock = new ReentrantLock(true);
    Condition fizzDone = lock.newCondition();
    Condition buzzDone = lock.newCondition();
    Condition fizzBuzzDone = lock.newCondition();
    Condition neitherDone = lock.newCondition();

    private int n;
    private int num = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    synchronized void printFizz() {
        while (num < n) {
            if (num % 3 == 0 && num % 5 != 0) {
                System.out.println("fizz");
                num++;
                notifyAll();
            } else {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    synchronized void printBuzz() {
        while (num < n) {
            if (num % 3 != 0 && num % 5 == 0) {
                System.out.println("Buzz");
                num++;
                notifyAll();
            } else {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    synchronized void printNeither() {
        while (num < n) {
            if (num % 3 != 0 && num % 5 != 0) {
                System.out.println(num);
                num++;
                notifyAll();
            } else {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    synchronized void

    printFizzBuzz() {
        while (num < n) {
            if (num % 13 == 0) {
                System.out.println("FizzBuzz");
                num++;
                notifyAll();
            } else {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        new Thread(() -> fizzBuzz.printFizz()).start();
        new Thread(() -> fizzBuzz.printBuzz()).start();
        new Thread(()-> fizzBuzz.printFizzBuzz()).start();
        new Thread(()-> fizzBuzz.printNeither()).start();
    }
}
