package threads;

/**
 * Created by susanneferraz on 18/09/16.
 */
public class Somador {

    private volatile boolean disponivel;
    private int x;
    private int y;

    public Somador() {
        this.disponivel = true;
        this.x = 0;
        this.y = 0;
    }


    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public synchronized int getX() {
        while (disponivel == false) {//
            try {
                //wait for set X value
                wait();
            } catch (InterruptedException e) { }
        }
        this.disponivel = false;
        notifyAll();
        return x;
    }

    public synchronized void setX(int x) {
        while (disponivel == true) {//alguem muda para falso
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        this.x = x;
        this.disponivel = true;

        notifyAll();


    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public synchronized void sum() {

        while (this.disponivel == true) {//
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        this.disponivel = false;//falso

        setX(getY() + 1);
        setY(getX() + 1);

        this.disponivel = true;

        notify();
    }
}
