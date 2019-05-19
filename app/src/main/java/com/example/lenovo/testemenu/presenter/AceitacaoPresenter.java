package com.example.lenovo.testemenu.presenter;

import android.util.Log;

import com.example.lenovo.testemenu.model.ApiResult;
import com.example.lenovo.testemenu.service.ClienteService;
import com.example.lenovo.testemenu.view.AceitacaoLocacaoView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AceitacaoPresenter {

    ApiResult result;
    ClienteService cService;
    AceitacaoLocacaoView aceitacaoView;

    public AceitacaoPresenter(ClienteService cService, AceitacaoLocacaoView aceitacaoView){
        this.cService = cService;
        this.aceitacaoView = aceitacaoView;
    }

    public void aceitarLocacao(int idSolicitacao){
        Log.d("trufa", idSolicitacao+"  ");
        cService.aceitarSolicitacao("aceitar", idSolicitacao).enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                result = response.body();
                if(result.isErro()){
                    Log.d("trufa", "sucesssssssssss");
                }else{
                    Log.d("trufa", "fracasss");
                }

            }

            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                Log.d("trufa", t.getMessage());
            }
        });

    }


}
