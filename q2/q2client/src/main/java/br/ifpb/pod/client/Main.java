/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpb.pod.client;

import br.ifpb.pod.entity.Pessoa;
import br.ifpb.pod.utils.DocumentUtils;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Natarajan
 */
public class Main {

    public static void main(String[] args) throws IOException {

        String query = "SELECT * FROM Pessoa";
        Cliente client = new Cliente();
        client.send(query);
        System.out.println("Query:"+ query);

        String jsonResult = client.receive();
        System.out.println("Json resultado que chegou :"+ jsonResult);

        List<Pessoa> resultList = DocumentUtils.getPessoasFromJson(jsonResult);


        System.out.println("\nA lista de pessoas encontrada pela query foi:");
        for (Pessoa p : resultList)
            System.out.println(p);


    }
    
}
