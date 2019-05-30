package com.example.lenovo.testemenu.presenter;

import android.util.Log;

import com.example.lenovo.testemenu.service.ClienteService;
import com.example.lenovo.testemenu.view.ViewUF;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterUF {

    ClienteService clienteService;
    ViewUF viewUF;
    String[] UFS;

    public  PresenterUF(ClienteService clienteService, ViewUF viewUF){
        this.clienteService = clienteService;
        this.viewUF = viewUF;
    }

    public void carregarUF(){
        Call<String[]> call = clienteService.listarUF("LISTAUF");
        call.enqueue(new Callback<String[]>() {
            @Override
            public void onResponse(Call<String[]> call, Response<String[]> response) {
                UFS = response.body();
                Log.d("nossa", UFS[1]);
                viewUF.sucessoUF(UFS);
            }

            @Override
            public void onFailure(Call<String[]> call, Throwable t) {
                Log.d("nossa", "fracass");
            }
        });
    }

}
