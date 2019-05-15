package com.example.lenovo.testemenu.view;

import com.example.lenovo.testemenu.model.AnuncioResult;

import java.util.List;

public interface FiltroView {
    void sucessoFiltro (List<AnuncioResult> anuncios);
    void pesquisaVazia();
    void semConexaoFiltro();
}
