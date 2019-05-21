package com.example.lenovo.testemenu.model;

public class VHistorico_Locacao {

    private int idLocacao;
    private int idCliente;
    private String nomeCliente;
    private int idDono;
    private String veiculo;
    private String horarioInicio;
    private String horarioFim;
    private float valor;
    private int devolvido;
    private int recebido;


    public int getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(int idLocacao) {
        this.idLocacao = idLocacao;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getIdDono() {
        return idDono;
    }

    public void setIdDono(int idDono) {
        this.idDono = idDono;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(String horarioFim) {
        this.horarioFim = horarioFim;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getDevolvido() {
        return devolvido;
    }

    public void setDevolvido(int devolvido) {
        this.devolvido = devolvido;
    }

    public int getRecebido() {
        return recebido;
    }

    public void setRecebido(int recebido) {
        this.recebido = recebido;
    }
}
