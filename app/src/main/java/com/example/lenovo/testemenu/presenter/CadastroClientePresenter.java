package com.example.lenovo.testemenu.presenter;

import android.util.Log;

import com.example.lenovo.testemenu.model.ApiResult;
import com.example.lenovo.testemenu.model.Cliente;
import com.example.lenovo.testemenu.service.ClienteService;
import com.example.lenovo.testemenu.view.CadastroView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroClientePresenter {

    ApiResult result;
    ClienteService cService;
    CadastroView ccView;


    public CadastroClientePresenter(ClienteService cService, CadastroView ccView){
        this.cService = cService;
        this.ccView = ccView;
    }

    public void chamarCadastrar(Cliente c){



        cService.inserir(c.getNome(), c.getCpf(), c.getCnh(), c.getCategoriaCnh(), c.getEmail(), c.getSenha(), c.getDataCadastro()).enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                result = response.body();
                if (result.isErro()){
                    Log.d("alo", "deu ruim");
                    ccView.fracasso();
                }else{
                    Log.d("alo", "deu bom");
                    ccView.sucesso();
                }

            }

            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                ccView.semConexao();
            }
        });
    }

}
