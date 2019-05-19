package com.example.lenovo.testemenu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lenovo.testemenu.MainActivity;
import com.example.lenovo.testemenu.R;
import com.example.lenovo.testemenu.adapter.PedidoLocacaoAdapter;
import com.example.lenovo.testemenu.model.VSolicitacaoLocacao;
import com.example.lenovo.testemenu.presenter.ListagemSolicitacaoLocacaoPresenter;
import com.example.lenovo.testemenu.service.ServiceFactory;
import com.example.lenovo.testemenu.view.ViewListagemSolicitacaoLocacao;

import java.util.List;

public class FragmentPedidoLocacao extends Fragment implements ViewListagemSolicitacaoLocacao{

    static String TAG = "FragmentPedidoLocacao";

    MainActivity activity;

    ListView lstLocacoes;
    ListagemSolicitacaoLocacaoPresenter presenter;

    PedidoLocacaoAdapter pedidoLocacaoAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pedido_locacao, container, false);

        presenter = new ListagemSolicitacaoLocacaoPresenter(this, ServiceFactory.create());



        activity = (MainActivity) getActivity();

        presenter.listarSolicitacoes(activity.idUsuarioLogado);

        lstLocacoes = v.findViewById(R.id.lstLocacoes);
        pedidoLocacaoAdapter = new PedidoLocacaoAdapter(getContext());
        lstLocacoes.setAdapter(pedidoLocacaoAdapter);




        return v;
    }

    @Override
    public void sucesso(List<VSolicitacaoLocacao> vSolicitacaoLocacao) {
        pedidoLocacaoAdapter.clear();;
        pedidoLocacaoAdapter.addAll(vSolicitacaoLocacao);
    }

    @Override
    public void semSolicitacao() {

    }
}
