package com.example.lenovo.testemenu.model;

import java.io.Serializable;
import java.util.List;

public class Veiculo implements Serializable {

    private int idVeiculo;
    private String nomeModelo;
    private String nomeMarca;
    private String cor;
    private float valor;
    private int ano;
    private float nota;
    private List<String> fotos;
    private List<String> acessorios;


    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getNomeModelo() {
        return nomeModelo;
    }

    public void setNomeModelo(String nomeModelo) {
        this.nomeModelo = nomeModelo;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    public List<String> getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(List<String> acessorios) {
        this.acessorios = acessorios;
    }
}
