package br.ifpb.pod.entity;

import org.bson.Document;
import org.json.JSONObject;

/**
 * Created by susanneferraz on 12/09/16.
 */
public class Pessoa {

    private int id;
    private String nome;
    private int idade;

    public Pessoa() {
    }

    public Pessoa(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Pessoa fromDocument(Document document) {

        this.id = document.getInteger("id");
        this.nome = document.getString("nome");
        this.idade = document.getInteger("idade");

        return this;
    }

    public Pessoa fromJSONObject(JSONObject object) {
        this.id = object.getInt("id");
        this.nome = object.getString("nome");
        this.idade = object.getInt("idade");

        return this;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }
}
