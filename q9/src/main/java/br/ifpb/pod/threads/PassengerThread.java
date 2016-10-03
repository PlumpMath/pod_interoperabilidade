package br.ifpb.pod.threads;

import br.ifpb.pod.interfaces.Passenger;

/**
 * Created by susanneferraz on 19/09/16.
 */
public class PassengerThread extends Thread implements Passenger {

    private String nome;
    private CarThread carrinho;



    public PassengerThread(String nome, CarThread car) {
        this.carrinho = car;
        this.nome = nome;
        this.carrinho = carrinho;

    }

    public String getNome() {
        return nome;
    }


    //entrar no carro
    public synchronized void getInTheCar() {

        while (carrinho.estahCheio()) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }

        synchronized (carrinho) {
            if (!carrinho.estahCheio())
                carrinho.adicionaUm();
        }

        System.out.println("<- Passageiro " + this.getNome() + " entrou no carro.");

    }


    //aguardar passeio finalizar
    public synchronized  void waitRideAway() {

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //sair do carro
    public synchronized void getOutTheCar() {

        System.out.println("-> Passageiro " + this.getNome() + " saiu do carro.");

    }


    //passear no parque
    public synchronized void rideInThePark() {

        //cada passeio no parque leva entre 1 e 3 ft
        System.out.println("Passageiro " + this.getNome() + " foi passear no parque.");
        int time = (int) (4 * Math.random());

        try {
            wait(1000 + 1000 * time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void run(){

        //repetir

        while (!carrinho.isTimeOver()) {

            //entrar no carro
            getInTheCar();

            //aguardar finalizar
            waitRideAway();

            //sair
            getOutTheCar();

            //passear
            rideInThePark();
        }
        this.interrupt();

    }

}
