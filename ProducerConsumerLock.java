import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerLock {
    public static void main(String[] args) {
        Worker worker = new Worker();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    worker.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    worker.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
        
    }
}

class Worker {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void producer() throws InterruptedException {
        lock.lock();
        System.out.println("Producer method.");
        condition.await();
        System.out.println("Producer after condition await.");
        lock.unlock();
    }

    public void consumer() throws InterruptedException {
        lock.lock();
        System.out.println("Consumer method.");
        Thread.sleep(2000);
        condition.signal();
        System.out.println("Consumer after condition signal.");
        lock.unlock();
    }
}