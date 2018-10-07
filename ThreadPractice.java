public class ThreadPractice {
    public static void main(String[] args) {
        Runner1 r1 = new Runner1();
        Runner2 r2 = new Runner2();
        r1.start();
        r2.start();
    }

    
}

class Runner1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runner 1: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }
            
        }
    }

}

class Runner2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runner 2: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }

}