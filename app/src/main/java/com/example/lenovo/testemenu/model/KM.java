package com.example.lenovo.testemenu.model;

public class KM {
    private String texto;
    private String km;

    public  KM(String texto, String km){
        this.texto = texto;
        this.km = km;
    }


    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    @Override
    public String toString()
    {
        return texto;
    }
}
