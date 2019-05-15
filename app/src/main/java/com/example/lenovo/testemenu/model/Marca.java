package com.example.lenovo.testemenu.model;

public class Marca {

    private int idMarca;
    private String nomeMarca;

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }

    @Override
    public String toString()
    {
        return nomeMarca;
    }
}
