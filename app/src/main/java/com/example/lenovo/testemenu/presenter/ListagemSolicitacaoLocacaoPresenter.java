package com.example.lenovo.testemenu.presenter;


import com.example.lenovo.testemenu.model.VSolicitacaoLocacao;
import com.example.lenovo.testemenu.service.ClienteService;
import com.example.lenovo.testemenu.view.ViewListagemSolicitacaoLocacao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListagemSolicitacaoLocacaoPresenter {

    ViewListagemSolicitacaoLocacao view;
    ClienteService service;
    List<VSolicitacaoLocacao> vsolicitacaoLocacao;

    public ListagemSolicitacaoLocacaoPresenter(ViewListagemSolicitacaoLocacao view, ClienteService service){
        this.service = service;
        this.view = view;
    }

    public void listarSolicitacoes(int idLocador){

        Call<List<VSolicitacaoLocacao>> call = service.listarSolicitacoes("listar", idLocador);

        call.enqueue(new Callback<List<VSolicitacaoLocacao>>() {
            @Override
            public void onResponse(Call<List<VSolicitacaoLocacao>> call, Response<List<VSolicitacaoLocacao>> response) {
                vsolicitacaoLocacao = response.body();
                view.sucesso(vsolicitacaoLocacao);
            }

            @Override
            public void onFailure(Call<List<VSolicitacaoLocacao>> call, Throwable t) {

            }
        });

    }


}
