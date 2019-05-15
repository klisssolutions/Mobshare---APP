package com.example.lenovo.testemenu.presenter;

import android.util.Log;

import com.example.lenovo.testemenu.model.AnuncioResult;
import com.example.lenovo.testemenu.service.ClienteService;
import com.example.lenovo.testemenu.service.ServiceFactory;
import com.example.lenovo.testemenu.view.AnunciosView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnunciosPresenter {

    AnunciosView anunciosView;
    ClienteService clienteService;

    List<AnuncioResult> anuncios;

    public AnunciosPresenter(AnunciosView anunciosView, ClienteService clienteService){
        this.anunciosView = anunciosView;
        this.clienteService = clienteService;
    }

    public void carregarAnuncios(){

        //ClienteService service = ServiceFactory.create();

        Call<List<AnuncioResult>> call = clienteService.obterAnuncios();

        call.enqueue(new Callback<List<AnuncioResult>>() {
            @Override
            public void onResponse(Call<List<AnuncioResult>> call, Response<List<AnuncioResult>> response) {

                anuncios = response.body();
                Log.d("marvel", anuncios.get(0).getNomeModelo());
                anunciosView.sucessoAnuncio(anuncios);
                Log.d("sucess", "opaaaaaaaaa");
            }

            @Override
            public void onFailure(Call<List<AnuncioResult>> call, Throwable t) {
                Log.d("ERRo", "pra variar");
            }
        });


    }
}
