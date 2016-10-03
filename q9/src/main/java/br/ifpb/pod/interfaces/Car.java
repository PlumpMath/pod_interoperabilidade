package br.ifpb.pod.interfaces;

/**
 * Created by susanneferraz on 19/09/16.
 */
public interface Car {

    void waitFill() throws InterruptedException;        //aguardar encher
    void takeAWalk();       //dar uma volta
    void waitGetOutAll();   //aguardar sair todos

}
