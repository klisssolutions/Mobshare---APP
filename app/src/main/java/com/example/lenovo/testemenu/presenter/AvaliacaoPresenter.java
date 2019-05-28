package com.example.lenovo.testemenu.presenter;

import android.util.Log;

import com.example.lenovo.testemenu.model.ApiResult;
import com.example.lenovo.testemenu.model.Avaliacao;
import com.example.lenovo.testemenu.service.ClienteService;
import com.example.lenovo.testemenu.view.AvaliacaoView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AvaliacaoPresenter {
    ClienteService clienteService;
    AvaliacaoView avaliacaoView;
    ApiResult apiResult;

    public  AvaliacaoPresenter(AvaliacaoView avaliacaoView, ClienteService clienteService){
        this.avaliacaoView = avaliacaoView;
        this.clienteService = clienteService;
    }

    public void avaliar(Avaliacao avaliacao, String tipoAvaliacao){
        Call<ApiResult> call = clienteService.avaliar("avaliar", avaliacao.getNota(), avaliacao.getDepoimento(),
                tipoAvaliacao, avaliacao.getIdLocacao());

        call.enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                apiResult = response.body();
                Log.d("sampa", "sucesss");
                avaliacaoView.sucessoAvaliacao();
            }

            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                Log.d("sampa", t.getMessage());
                avaliacaoView.fracassoAvaliacao();
            }
        });

    }

}
