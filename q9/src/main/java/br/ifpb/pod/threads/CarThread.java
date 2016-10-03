package br.ifpb.pod.threads;

import br.ifpb.pod.interfaces.Car;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by susanneferraz on 19/09/16.
 */
public class CarThread extends Thread implements Car {


    private volatile boolean timeOver;
    private List<PassengerThread> passageiros;
    private final int lotacao;
    private int quantidade;

    public CarThread(int lotacao) {
        this.lotacao = lotacao;
        this.timeOver = false;
        this.passageiros = new LinkedList<PassengerThread>();

    }

    public boolean isTimeOver(){
        return this.timeOver;
    }

    public synchronized void adicionaUm(){
        synchronized (this) {
            if (!estahCheio()) {
                quantidade++;
            }

            if (estahCheio())
                notifyAll();
        }

    }

    public synchronized boolean estahCheio(){
        synchronized (this){
            return quantidade == lotacao;
        }

    }

    public void add (PassengerThread passengerThread) {
        passageiros.add(passengerThread);
    }

    public void remove (PassengerThread passengerThread) {
        passageiros.remove(passengerThread);
    }

    //aguardar encher
    public synchronized void waitFill(){
        System.out.println("Esperando carrinho encher...");

        while (!estahCheio()) {
            try {

                wait(); //wait for fill

            } catch (InterruptedException e) { }
        }

        if (estahCheio()) {
            System.out.println("Tá cheio: " + quantidade);
        }

    }


    //dar uma volta
    public synchronized void takeAWalk() {
        //cada volta no carro leva 1ft
        System.out.println("Montanha russa dando uma volta...");

        synchronized (this) {
            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("fim da volta...");
    }

    //esperar todos sairem
    public synchronized void waitGetOutAll() {
        System.out.println("Esvaziando carrinho");
        synchronized (this) {
            quantidade = 0;
        }

        for (PassengerThread p : passageiros) {
            synchronized (p) {
                p.notifyAll();
            }
        }

    }

    @Override
    public void run(){
        //repetir durante 16ft

        new Thread(){

            @Override
            public void run() {

                try {
                    Thread.sleep(16 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timeOver = true;
                System.out.println("parque fechou");
            }
        }.start();

        while(!isTimeOver()) {

            waitFill();         //esperar encher

            takeAWalk();        //dar uma volta

            waitGetOutAll();    //esperar todos sairem
        }

        System.out.println("over");

    }
}
