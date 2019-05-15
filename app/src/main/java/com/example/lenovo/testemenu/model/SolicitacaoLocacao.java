package com.example.lenovo.testemenu.model;

public class SolicitacaoLocacao{

    private int idSolicitacao_Locacao;
    private int idCliente;
    private int idVeiculo;
    private Boolean confirmLocador;
    private String horarioInicio;
    private String horarioFim;
    private String motivoRecusa;


    public int getIdSolicitacao_Locacao() {
        return idSolicitacao_Locacao;
    }

    public void setIdSolicitacao_Locacao(int idSolicitacao_Locacao) {
        this.idSolicitacao_Locacao = idSolicitacao_Locacao;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Boolean getConfirmLocador() {
        return confirmLocador;
    }

    public void setConfirmLocador(Boolean confirmLocador) {
        this.confirmLocador = confirmLocador;
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

    public String getMotivoRecusa() {
        return motivoRecusa;
    }

    public void setMotivoRecusa(String motivoRecusa) {
        this.motivoRecusa = motivoRecusa;
    }
}
