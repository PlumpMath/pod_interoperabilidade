package q7;

/**
 * Created by susanneferraz on 18/09/16.
 */
public class Somador {

    private boolean disponivel;
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

    public synchronized void sum() {

        synchronized (this) {
            setX(getY() + 1);
            setY(getX() + 1);

        }

    }
}
