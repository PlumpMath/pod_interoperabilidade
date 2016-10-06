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
public class Main2 {

    static int x = 0;
    static int y = 0;

    public static void main(String[] args) throws InterruptedException {


        Thread lastThread = null;


        Thread c = new Thread(){
            Thread a, b = null;
            public void run (){
                for (int i = 0; i < 10; i++ ) {

                    a = new Thread(){
                        public  void run() {

                            x = y + 1;
                            y = x + 1;

                            synchronized (this) {
                                try {
                                    wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }


                            }
                        }
                    };



                    b = new Thread(){
                        public  void run() {

                            x = y + 1;
                            y = x + 1;
                            System.out.println(x);

                            synchronized (this) {
                                try {
                                    wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    };


                    a.start();
                    b.start();

                }
            }
        };
        c.start();

        Thread.sleep(100);

        System.out.println("Resultado final do valor de x: " + x);

    }

}
