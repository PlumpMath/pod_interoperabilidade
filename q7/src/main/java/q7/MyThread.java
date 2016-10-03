package q7;

/**
 * Created by susanneferraz on 18/09/16.
 */
public class MyThread extends Thread {

    private Somador somador;

    public MyThread(Somador somador) {

        super();

        this.somador = somador;

    }

    @Override
    public void run() {

        synchronized (somador) {
            somador.sum();
        }

    }

}
