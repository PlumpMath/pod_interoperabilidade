/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpb.pod.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 *
 * @author Natarajan
 */
public class Cliente {

    private String query = "this is a query";
    private final Socket socket;

    public Cliente() throws IOException {
        this.socket = new Socket("localhost", 12345);

    }
    
    
    
    public void send(String message) throws IOException{
        System.out.println("Client: enviando mensagem...");
        String s = message;
        socket.getOutputStream().write(s.getBytes());
        socket.getOutputStream().flush();   //no flush, quando tudo for enviado é que o programa continua
        System.out.println("Client: encerrando socket...");
//        socket.close();
    }
    
    public String receive() throws IOException{
        System.out.println("Cliente recebendo mensagem...");
        
        InputStream input = socket.getInputStream();
        byte[] b = new byte[1024];
        input.read(b);
        return new String(b).trim();
    }
    
}
