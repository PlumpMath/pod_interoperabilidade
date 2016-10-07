package threads;

/**
 * Created by Natarajan on 17/09/16.
 *
 * Professor, esta aqui não é a resposta. Deixei o código aqui para vislumbre posterior, para estudo e aprimoramento
 * sobre o assunto, visto que quebrei muita cabeça tentando resolver desta forma e o assunto é interessante.
 * Favor observar os outros Main's.
 * Atenciosamente,
 * Natarajan
 *
 */
public class Main {



    public static void main(String[] args){


        Somador somador = new Somador();

        for (int i = 0; i < 10; i++ ) {


            Thread a = new MyThread2(somador);
            Thread b = new MyThread2(somador);


            a.start();
            b.start();

            System.out.println(somador.getX());

        }

        System.out.println("Resultado final do valor de x: " + somador.getX());

    }

}
