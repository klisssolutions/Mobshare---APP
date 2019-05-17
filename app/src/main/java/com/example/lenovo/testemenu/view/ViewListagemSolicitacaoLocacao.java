package com.example.lenovo.testemenu.view;

import com.example.lenovo.testemenu.model.VSolicitacaoLocacao;

import java.util.List;

public interface ViewListagemSolicitacaoLocacao {
    void sucesso(List<VSolicitacaoLocacao> vSolicitacaoLocacao);
    void semSolicitacao();

}
