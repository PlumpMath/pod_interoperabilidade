package threads;

/**
 * Created by Natarajan on 17/09/16.
 *
 *
 * Resposta à questão:
 *
 * Também devido à concorrência resultante da execução paralela das Threads A e B, o valor de x ao final de uma execução
 * da codificação proposta pode variar.
 *
 * Os resultados que obtivemos foram: 35, 37 ou 39.
 *
 */
public class Main4 {

    static int x = 0;
    static int y = 0;

    public static void main(String[] args) throws InterruptedException {


        Thread lastThread = null;


        Thread c = new Thread(){
            Thread a, b = null;

            public void run (){
                for (int i = 0; i < 10; i++ ) {

                    int xantigo = x;

                    a = new Thread(){
                        public  void run() {
                            x = y + 1;
                            y = x + 1;
                            System.out.println("x1 : " + x);
                        }
                    };
                    a.start();

                    b = new Thread(){
                        public  void run() {
                            x = y + 1;
                            y = x + 1;
                            System.out.println("x2 : " + x);
                        }
                    };
                    b.start();

                    synchronized (this) {

                        while (x < xantigo + 3) {

                            try {
                                wait(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }


                }
            }
        };
        c.start();

        Thread.sleep(100);

        System.out.println("Resultado final do valor de x: " + x);

    }

}
