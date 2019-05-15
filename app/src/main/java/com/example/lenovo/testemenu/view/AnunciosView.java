package com.example.lenovo.testemenu.view;

import com.example.lenovo.testemenu.model.AnuncioResult;

import java.util.List;

public interface AnunciosView {
    //faz a conexao entre a presenter e a activity
    void sucessoAnuncio(List<AnuncioResult> lstAnuncios);
}
