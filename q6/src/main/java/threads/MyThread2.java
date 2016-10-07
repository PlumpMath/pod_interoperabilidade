package threads;

/**
 * Created by susanneferraz on 18/09/16.
 */
public class MyThread2 extends Thread {

    private Somador somador;
    private static int i = 0;

    public MyThread2(Somador somador) {

        super();

        this.somador = somador;

    }

    @Override
    public void run() {

        synchronized (somador) {
            System.out.println("somou: " + i++);
            somador.sum();
        }

    }

}
