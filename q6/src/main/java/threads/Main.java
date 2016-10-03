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
public class Main {



    public static void main(String[] args){


        Somador somador = new Somador();

        for (int i = 0; i <= 10; i++ ) {


            Thread a = new MyThread2(somador);
            Thread b = new MyThread2(somador);


            a.start();
            b.start();

            System.out.println(somador.getX());

        }

        System.out.println("Resultado final do valor de x: " + somador.getX());

    }

}
