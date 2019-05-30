package com.example.lenovo.testemenu.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.testemenu.MainActivity;
import com.example.lenovo.testemenu.R;
import com.example.lenovo.testemenu.fragment.FragmentAvaliacao;
import com.example.lenovo.testemenu.model.VHistorico_Locacao;
import com.example.lenovo.testemenu.presenter.DevolverPresenter;
import com.example.lenovo.testemenu.presenter.ReceberPresenter;
import com.example.lenovo.testemenu.service.ServiceFactory;
import com.example.lenovo.testemenu.utils.Alert;
import com.example.lenovo.testemenu.view.DevolverView;
import com.example.lenovo.testemenu.view.ReceberView;

import java.util.ArrayList;

public class HistoricoAdapter extends ArrayAdapter<VHistorico_Locacao> implements ReceberView, DevolverView{

    MainActivity activity;

    FragmentAvaliacao fragmentAvaliacao;


    ReceberPresenter receberPresenter;
    DevolverPresenter devolverPresenter;

    Alert alert;


    //criando a lista dos anuncios
    public HistoricoAdapter(Context ctx, MainActivity activity){
        super(ctx, 0, new ArrayList<VHistorico_Locacao>());
        alert = new Alert(getContext());
        this.activity = activity;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;


        if (v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_historico_locacao_layout, parent, false);
        }

        final VHistorico_Locacao vHistorico_Locacao = getItem(position);

        TextView txtVeiculo = v.findViewById(R.id.txtVeiculo);
        TextView txtInicio = v.findViewById(R.id.txtInicio);
        TextView txtFim = v.findViewById(R.id.txtFim);
        TextView txtValor = v.findViewById(R.id.txtValor);
        final Button btnConfirmacao = v.findViewById(R.id.btnConfimacao);



        if(vHistorico_Locacao.getIdDono() == activity.idUsuarioLogado && vHistorico_Locacao.getRecebido() == 0){
            btnConfirmacao.setText("Receber");
            Log.d("tcc", "Receber"+activity.idUsuarioLogado +", " +vHistorico_Locacao.getIdLocacao()+", "+ vHistorico_Locacao.getRecebido()+"");
        }else if(vHistorico_Locacao.getIdDono() != activity.idUsuarioLogado && vHistorico_Locacao.getDevolvido() == 0){
            btnConfirmacao.setText("Devolver");
            Log.d("tcc", "Receber"+activity.idUsuarioLogado +", " +vHistorico_Locacao.getIdLocacao()+", "+ vHistorico_Locacao.getRecebido()+"");
        }else{
            btnConfirmacao.setVisibility(View.INVISIBLE);
            Log.d("tcc", "aaaa"+activity.idUsuarioLogado +", " +vHistorico_Locacao.getIdLocacao()+", "+ vHistorico_Locacao.getRecebido()+"");
        }

        txtVeiculo.setText(vHistorico_Locacao.getVeiculo());
        txtInicio.setText(vHistorico_Locacao.getHorarioInicio());
        txtFim.setText(vHistorico_Locacao.getHorarioFim());
        txtValor.setText(vHistorico_Locacao.getValor()+"");

        btnConfirmacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("kliss", "clicado");
                if(btnConfirmacao.getText() == "Receber"){
                    Log.d("kliss", "entrou");
                    receberPresenter = new ReceberPresenter(HistoricoAdapter.this, ServiceFactory.create());
                    receberPresenter.receberVeiculo(vHistorico_Locacao.getIdLocacao());

                }else if(btnConfirmacao.getText() == "Devolver"){
                    devolverPresenter = new DevolverPresenter(HistoricoAdapter.this, ServiceFactory.create());
                    devolverPresenter.devolverVeiculo(vHistorico_Locacao.getIdLocacao());
                }
            }
        });

        return v;
    }

    @Override
    public void sucessoReceber(int idLocacao) {

        alert.alert("Pronto!", "Você confirmou o recebimento do veiculo", "Ok");
        fragmentAvaliacao = new FragmentAvaliacao();
        Bundle bundle = new Bundle();
        bundle.putString("tipoAvaliacao", "cliente");
        bundle.putInt("idLocacao", idLocacao);
        fragmentAvaliacao.setArguments(bundle);

        activity.navegar(fragmentAvaliacao, fragmentAvaliacao.TAG);
    }

    @Override
    public void fracassoReceber() {
        alert.alert("Ops!", "Houve um erro na confirmação do recebimento do veiculo", "Ok");
    }

    @Override
    public void sucessoDevolver() {
        alert.alert("Pronto!", "Você confirmou a devolução do veiculo", "Ok");
        fragmentAvaliacao = new FragmentAvaliacao();
        activity.navegar(fragmentAvaliacao, fragmentAvaliacao.TAG);

    }

    @Override
    public void fracassoDevolver() {

    }
}