package com.example.lenovo.testemenu.presenter;

import android.util.Log;

import com.example.lenovo.testemenu.model.Veiculo;
import com.example.lenovo.testemenu.service.ClienteService;
import com.example.lenovo.testemenu.view.VeiculoView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VeiculoPresenter {


    VeiculoView view;
    ClienteService service;
    Veiculo veiculo;

    public VeiculoPresenter(VeiculoView view, ClienteService service){
        this.service = service;
        this.view = view;
    }


    public void buscarVeiculo(int idVeiculo){
        Call<Veiculo> call = service.buscarVeiculo("buscar", idVeiculo);

        call.enqueue(new Callback<Veiculo>() {
            @Override
            public void onResponse(Call<Veiculo> call, Response<Veiculo> response) {
                veiculo = response.body();
                Log.d("leo", veiculo.getNomeMarca());
                view.sucessoVeiculo(veiculo);
            }

            @Override
            public void onFailure(Call<Veiculo> call, Throwable t) {
                Log.d("leo", t.getMessage());
            }
        });
    }

}
