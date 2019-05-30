package com.example.lenovo.testemenu.presenter;

import com.example.lenovo.testemenu.service.ClienteService;
import com.example.lenovo.testemenu.view.ViewCidade;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterCidade {
    ClienteService clienteService;
    ViewCidade viewCidade;
    String[] cidades;

    public PresenterCidade(ClienteService clienteService, ViewCidade viewCidade){
        this.clienteService = clienteService;
        this.viewCidade = viewCidade;
    }

    public  void carregarCidades(String UF){
        Call<String[]> call = clienteService.listarCidades("LISTACIDADE", UF);
        call.enqueue(new Callback<String[]>() {
            @Override
            public void onResponse(Call<String[]> call, Response<String[]> response) {
                cidades = response.body();
                viewCidade.sucessoCidade(cidades);
            }

            @Override
            public void onFailure(Call<String[]> call, Throwable t) {
                viewCidade.fracassoCidade();
            }
        });
    }

}
