package threads;

/**
 * Created by Natarajan Rodrigues on 06/10/16.
 */
public class Main6 {

    //inspirada num solução do colega victor hugo, com algumas poucas alterações sugestivas.

    private volatile int x = 0, y = 0;
    private ThreadA threadA;

    private void sum() {

        x = y + 1;
        y = x + 1;

    }

    private void buildThreads() {

        threadA = new ThreadA();

    }

    private void startThreads() throws InterruptedException {

        threadA.start();

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static void main(String[] args) throws InterruptedException {

        Main6 loader = new Main6();

        for (int i = 0; i < 10; i++) {

            loader.buildThreads();

            loader.startThreads();
        }

        Thread.sleep(3000);
        System.out.println(loader.getX());

    }

    public class ThreadA extends Thread {

        ThreadB threadB = new ThreadB();

        @Override
        public void run() {

            threadB.start();

            synchronized (threadB) {
                try {
                    threadB.wait();

                } catch (InterruptedException ex) {
                }

                sum();

                threadB.notifyAll();
            }

        }

    }

    public class ThreadB extends Thread {

        @Override
        public void run() {

            synchronized (this) {

                sum();
                notify();
            }

        }

    }

}
