package com.example.lenovo.testemenu.presenter;

import android.util.Log;

import com.example.lenovo.testemenu.model.ApiResult;
import com.example.lenovo.testemenu.service.ClienteService;
import com.example.lenovo.testemenu.view.DevolverView;
import com.example.lenovo.testemenu.view.ReceberView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DevolverPresenter {
    DevolverView devolverView;


    ClienteService clienteService;
    ApiResult apiResult;

    public DevolverPresenter(DevolverView devolverView, ClienteService clienteService){
        this.devolverView = devolverView;
        this.clienteService = clienteService;
    }

    public void devolverVeiculo(int idLocacao){



        Call<ApiResult> call = clienteService.devolver("devolver", idLocacao);

        call.enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                apiResult = response.body();
                Log.d("trui", "deve resposta");

                if(!apiResult.isErro()){

                    devolverView.sucessoDevolver();

                }else{

                    devolverView.fracassoDevolver();
                }


            }

            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                Log.d("trui", "nem volto");
                Log.d("ERRo", t.getLocalizedMessage());
            }
        });


    }
}
