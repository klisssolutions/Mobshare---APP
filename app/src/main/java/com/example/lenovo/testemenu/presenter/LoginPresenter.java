package com.example.lenovo.testemenu.presenter;

import android.util.Log;

import com.example.lenovo.testemenu.model.LoginResult;
import com.example.lenovo.testemenu.service.ClienteService;
import com.example.lenovo.testemenu.view.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginPresenter {

    LoginResult loginResult;
    LoginView lgView;
    ClienteService clienteService;

    public LoginPresenter(ClienteService clienteService, LoginView lgView){
        this.lgView = lgView;
        this.clienteService = clienteService;
    }


    public void mandarParaLogin(String email, String senha){



        clienteService.logar(email, senha).enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                loginResult = response.body();



                if(loginResult.getIdCliente() != 0){
                    Log.d("hehe", loginResult.getIdCliente()+"");
                    Log.d("haha", loginResult.getNomeCliente());
                    lgView.logou(loginResult.getNomeCliente(), loginResult.getIdCliente());
                }else{
                    lgView.naoLogou();
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.e("ERRO_API", t.getMessage());
                lgView.semConexao();
            }
        });

    }


}
