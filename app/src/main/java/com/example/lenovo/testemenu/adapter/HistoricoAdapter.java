package com.example.lenovo.testemenu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.testemenu.MainActivity;
import com.example.lenovo.testemenu.R;
import com.example.lenovo.testemenu.model.VHistorico_Locacao;

import java.util.ArrayList;

public class HistoricoAdapter extends ArrayAdapter<VHistorico_Locacao> {

    MainActivity activity;


    //criando a lista dos anuncios
    public HistoricoAdapter(Context ctx, MainActivity activity){
        super(ctx, 0, new ArrayList<VHistorico_Locacao>());
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
        Button btnConfirmacao = v.findViewById(R.id.btnConfimacao);



        if(vHistorico_Locacao.getIdDono() == activity.idUsuarioLogado && vHistorico_Locacao.getRecebido() == 0){
            btnConfirmacao.setText("Receber");
        }else if(vHistorico_Locacao.getIdDono() != activity.idUsuarioLogado && vHistorico_Locacao.getDevolvido() == 0){
            btnConfirmacao.setText("Devolver");
        }else{
            btnConfirmacao.setVisibility(View.INVISIBLE);
        }

        txtVeiculo.setText(vHistorico_Locacao.getVeiculo());
        txtInicio.setText(vHistorico_Locacao.getHorarioInicio());
        txtFim.setText(vHistorico_Locacao.getHorarioFim());
        txtValor.setText(vHistorico_Locacao.getValor()+"");

        return v;
    }

}