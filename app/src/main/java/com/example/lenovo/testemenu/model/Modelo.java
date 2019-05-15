package com.example.lenovo.testemenu.model;

public class Modelo {
    private int idModelo;
    private String nomeModelo;

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public String getNomeModelo() {
        return nomeModelo;
    }

    public void setNomeModelo(String nomeModelo) {
        this.nomeModelo = nomeModelo;
    }

    @Override
    public String toString(){
        return nomeModelo;
    }

}
