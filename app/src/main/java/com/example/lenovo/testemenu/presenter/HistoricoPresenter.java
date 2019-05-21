package com.example.lenovo.testemenu.presenter;

import android.util.Log;

import com.example.lenovo.testemenu.model.AnuncioResult;
import com.example.lenovo.testemenu.model.VHistorico_Locacao;
import com.example.lenovo.testemenu.service.ClienteService;
import com.example.lenovo.testemenu.view.AnunciosView;
import com.example.lenovo.testemenu.view.DevolverView;
import com.example.lenovo.testemenu.view.HistoricoView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoricoPresenter{

    HistoricoView historicoView;
    ClienteService clienteService;

    List<VHistorico_Locacao> vHistorico_locacaos;

    public HistoricoPresenter(HistoricoView historicoView, ClienteService clienteService){
        this.historicoView = historicoView;
        this.clienteService = clienteService;
    }

    public void listarHistorico(int idLogado){



        Call<List<VHistorico_Locacao>> call = clienteService.listarHistorico("listarhistorico", idLogado);

        call.enqueue(new Callback<List<VHistorico_Locacao>>() {
            @Override
            public void onResponse(Call<List<VHistorico_Locacao>> call, Response<List<VHistorico_Locacao>> response) {
                vHistorico_locacaos = response.body();
                Log.d("ERRo", vHistorico_locacaos.get(0).getVeiculo());

                historicoView.sucesso(vHistorico_locacaos);
            }

            @Override
            public void onFailure(Call<List<VHistorico_Locacao>> call, Throwable t) {
                Log.d("ERRo", t.getMessage());
            }
        });


    }
}
