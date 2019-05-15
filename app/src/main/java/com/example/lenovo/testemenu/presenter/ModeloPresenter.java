package com.example.lenovo.testemenu.presenter;

import android.util.Log;

import com.example.lenovo.testemenu.model.Modelo;
import com.example.lenovo.testemenu.service.ClienteService;
import com.example.lenovo.testemenu.view.ModeloView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModeloPresenter {

    ClienteService service;
    ModeloView view;
    List<Modelo> modelos;

    public ModeloPresenter(ModeloView view, ClienteService service){
        this.view = view;
        this.service = service;

    }

    public void carregarModelos(){
        Call<List<Modelo>> call = service.obterModelos();

        call.enqueue(new Callback<List<Modelo>>() {
            @Override
            public void onResponse(Call<List<Modelo>> call, Response<List<Modelo>> response) {
                modelos = response.body();
               // Log.e("nao", modelos.get(0).getNomeModelo());
                view.sucessoModelo(modelos);
            }

            @Override
            public void onFailure(Call<List<Modelo>> call, Throwable t) {

            }
        });
    }
}
