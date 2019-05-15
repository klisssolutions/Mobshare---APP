package com.example.lenovo.testemenu.presenter;

import android.util.Log;

import com.example.lenovo.testemenu.model.ApiResult;
import com.example.lenovo.testemenu.model.Cliente;
import com.example.lenovo.testemenu.model.SolicitacaoLocacao;
import com.example.lenovo.testemenu.service.ClienteService;
import com.example.lenovo.testemenu.view.CadastroView;
import com.example.lenovo.testemenu.view.ViewSolicitacaoLocacao;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SolicitacaoLocacaoPresenter {



    ApiResult result;
    ClienteService cService;
    ViewSolicitacaoLocacao slView;


    public SolicitacaoLocacaoPresenter(ClienteService cService, ViewSolicitacaoLocacao slView){
        this.cService = cService;
        this.slView = slView;
    }

    public void solicitarLocacao(SolicitacaoLocacao solicitacaoLocacao){



        cService.inserirSolicitacao_Locacao("INSERIR", solicitacaoLocacao.getIdCliente(), solicitacaoLocacao.getIdVeiculo(),
                solicitacaoLocacao.getHorarioInicio(), solicitacaoLocacao.getHorarioFim()).enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                result = response.body();

                if (result.isErro()){
                    Log.d("roty", "sucesso");
                    slView.sucesso();
                }else{
                    Log.d("roty", response.toString());
                    slView.fracasso();
                }
            }

            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                Log.d("roty", t.getMessage());
            }
        });


    }

}
