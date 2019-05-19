package com.example.lenovo.testemenu.presenter;

import android.util.Log;

import com.example.lenovo.testemenu.model.ApiResult;
import com.example.lenovo.testemenu.service.ClienteService;
import com.example.lenovo.testemenu.view.RecusaLocacaoView;
import com.example.lenovo.testemenu.view.ViewSolicitacaoLocacao;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecusaPresenter {



    ApiResult result;
    ClienteService service;

    RecusaLocacaoView view;


    public RecusaPresenter(ClienteService service, RecusaLocacaoView view){
        this.view = view;
        this.service = service;

    }

    public void recusarLocacao(int idSolicitacao){

        service.recusarSolicitacao("recusar", idSolicitacao).enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                result = response.body();
                Log.d("trufa", "sucesssssssssss");
            }

            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                Log.d("trufa", t.getMessage());
            }
        });

    }

}
