package q4;

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
public class App {

    //declaração
    private static int x = 0;
    private static int y = 0;


    //procedimento
    public static void sum() {
        x = y + 1;
        y = x + 1;
    }



    public static void main(String[] args) {


        for (int i = 1; i <= 10; i++ ) {

            //Thread A
            Thread a = new Thread() {

                public void run() {

                        sum();

                }

            };
            a.start();


            //Thread B
            Thread b = new Thread() {
                public void run() {

                    sum();

                }
            };
            b.start();


            System.out.println(x);

        }

    }

}
