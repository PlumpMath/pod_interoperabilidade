package q7;

/**
 * Hello world!
 *
 */

public class App {

    //declaração
    private static int x = 0;
    private static int y = 0;


    //procedimento




    public static void main(String[] args) throws InterruptedException {

        Somador somador = new Somador();


        for (int i = 1; i <= 10; i++ ) {

            //Thread A
            Thread a = new MyThread(somador);
            Thread b = new MyThread(somador);

            synchronized (somador) {
                a.start();
                b.start();

                System.out.println(somador.getX());
            }



        }

        Thread.sleep(100);
        System.out.println("Resultado final de x: " + somador.getX());

    }

}
