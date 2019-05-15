package com.example.lenovo.testemenu.model;

public class LoginResult {
    //@SerializedName("id") se usa essa notacao quando o atributo nao tem o mesmo nome de retorno daa api

    private int idCliente;
    private String nomeCliente;


    public int getIdCliente(){
        return idCliente;
    }

    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

}
