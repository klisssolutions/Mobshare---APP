package com.example.lenovo.testemenu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.testemenu.MainActivity;
import com.example.lenovo.testemenu.R;
import com.example.lenovo.testemenu.model.AnuncioResult;
import com.example.lenovo.testemenu.model.VSolicitacaoLocacao;
import com.example.lenovo.testemenu.presenter.AceitacaoPresenter;
import com.example.lenovo.testemenu.presenter.RecusaPresenter;
import com.example.lenovo.testemenu.presenter.SolicitacaoLocacaoPresenter;
import com.example.lenovo.testemenu.service.ServiceFactory;
import com.example.lenovo.testemenu.view.AceitacaoLocacaoView;
import com.example.lenovo.testemenu.view.RecusaLocacaoView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PedidoLocacaoAdapter extends ArrayAdapter<VSolicitacaoLocacao> implements AceitacaoLocacaoView, RecusaLocacaoView {

    MainActivity activity;
    AceitacaoPresenter aceitacaoPresenter;
    RecusaPresenter recusaPresenter;

    //criando a lista dos anuncios
    public PedidoLocacaoAdapter(Context ctx){
        super(ctx, 0, new ArrayList<VSolicitacaoLocacao>());

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if (v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_pedido_locacao_layout, parent, false);
        }

        final VSolicitacaoLocacao vSolicitacaoLocacao = getItem(position);

        //instanciando e já preenchendo com os dados
        TextView txtVeiculo = v.findViewById(R.id.txtVeiculo);
        TextView txtLocatario = v.findViewById(R.id.txtLocatario);
        TextView txtInicio = v.findViewById(R.id.txtInicio);
        TextView txtFim = v.findViewById(R.id.txtFim);

        Button btnAceitar = v.findViewById(R.id.btnAceitar);
        Button btnRecusar = v.findViewById(R.id.btnRecusar);

        txtVeiculo.setText(vSolicitacaoLocacao.getVeiculo());
        txtLocatario.setText(vSolicitacaoLocacao.getNomeCliente());
        txtInicio.setText(vSolicitacaoLocacao.getHorarioInicio());
        txtFim.setText(vSolicitacaoLocacao.getHorarioFim());

        btnAceitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("loving", vSolicitacaoLocacao.getIdSolicitacao_Locacao()+"");
                aceitacaoPresenter = new AceitacaoPresenter(ServiceFactory.create(), PedidoLocacaoAdapter.this);
                aceitacaoPresenter.aceitarLocacao(vSolicitacaoLocacao.getIdSolicitacao_Locacao());
            }
        });


        btnRecusar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("loving", vSolicitacaoLocacao.getIdSolicitacao_Locacao()+"");
                recusaPresenter = new RecusaPresenter(ServiceFactory.create(), PedidoLocacaoAdapter.this);
                recusaPresenter.recusarLocacao(vSolicitacaoLocacao.getIdSolicitacao_Locacao());
            }
        });

        return v;
    }

    @Override
    public void sucessoAceite() {

    }

    @Override
    public void fracassoAceite() {

    }

    @Override
    public void sucessoRecusa() {

    }
}
