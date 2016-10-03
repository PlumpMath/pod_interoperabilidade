package br.ifpb.pod.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by susanneferraz on 20/09/16.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {


        System.out.println("Defina o tamanho do carrinho (> 20): ");
        Scanner in = new Scanner(System.in);
        int c;

        do {
            c = in.nextInt();

            CarThread carThread = new CarThread(c);

            List<PassengerThread> passageiros = new ArrayList<>();

            int n = (int) (c / (0.875));

            for (int i = 0; i < n; i++) {
                PassengerThread p = new PassengerThread("nº " + (i + 1), carThread);
                carThread.add(p);
                passageiros.add(p);

            }

            boolean fechou = false;


            carThread.start();
            for (PassengerThread p : passageiros)
                p.start();



        } while (c != 0);










    }

}
