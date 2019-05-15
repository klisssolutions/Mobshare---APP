package com.example.lenovo.testemenu.presenter;



import android.util.Log;

import com.example.lenovo.testemenu.model.AnuncioResult;
import com.example.lenovo.testemenu.model.ApiResult;
import com.example.lenovo.testemenu.model.Filtro;
import com.example.lenovo.testemenu.service.ClienteService;
import com.example.lenovo.testemenu.view.FiltroView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterFiltro {

    List<AnuncioResult> veiculos;

    FiltroView view;
    ClienteService service;

    public PresenterFiltro(FiltroView view, ClienteService service){
        this.service = service;
        this.view = view;
    }

    public void carregarAnuncios(Filtro filtro){
        Call<List<AnuncioResult>> call = service.filtrarAnuncios("filtrar", filtro.getIdMarca(), filtro.getIdModelo(),
                 filtro.getKm(), filtro.getAvaliacao(), filtro.getIdTipoVeiculo());



        call.enqueue(new Callback<List<AnuncioResult>>() {
            @Override
            public void onResponse(Call<List<AnuncioResult>> call, Response<List<AnuncioResult>> response) {
                veiculos = response.body();
                if (veiculos.size() > 0){
                    Log.d("pai", veiculos.get(0).getNomeModelo());
                    view.sucessoFiltro(veiculos);
                }

            }

            @Override
            public void onFailure(Call<List<AnuncioResult>> call, Throwable t) {
                view.pesquisaVazia();
            }
        });
    }


}
