package com.example.lenovo.testemenu.model;

public class VSolicitacaoLocacao {

    private int idSolicitacao_Locacao;
    private int idCliente;
    private String nomeCliente;
    private int idDono;
    private String veiculo;
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

    public String getMotivoRecusa() {
        return motivoRecusa;
    }

    public void setMotivoRecusa(String motivoRecusa) {
        this.motivoRecusa = motivoRecusa;
    }
}
