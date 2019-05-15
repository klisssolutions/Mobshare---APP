package com.example.lenovo.testemenu.presenter;

import android.util.Log;

import com.example.lenovo.testemenu.model.Marca;
import com.example.lenovo.testemenu.service.ClienteService;
import com.example.lenovo.testemenu.view.MarcaView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarcaPresenter {

    MarcaView view;
    ClienteService service;
    List<Marca> marcas;

    public MarcaPresenter(MarcaView view, ClienteService service){
        this.service = service;
        this.view = view;
    }


    public void carregarMarcas(){
        Call<List<Marca>> call = service.obterMarcas();

        call.enqueue(new Callback<List<Marca>>() {
            @Override
            public void onResponse(Call<List<Marca>> call, Response<List<Marca>> response) {
                marcas = response.body();
                Log.d("leo", marcas.get(0).getNomeMarca());
                view.sucessoMarca(marcas);
            }

            @Override
            public void onFailure(Call<List<Marca>> call, Throwable t) {

            }
        });
    }


}
