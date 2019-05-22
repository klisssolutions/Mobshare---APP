package com.example.lenovo.testemenu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lenovo.testemenu.MainActivity;
import com.example.lenovo.testemenu.R;
import com.example.lenovo.testemenu.adapter.HistoricoAdapter;
import com.example.lenovo.testemenu.model.VHistorico_Locacao;
import com.example.lenovo.testemenu.model.Veiculo;
import com.example.lenovo.testemenu.presenter.HistoricoPresenter;
import com.example.lenovo.testemenu.service.ServiceFactory;
import com.example.lenovo.testemenu.view.HistoricoView;

import java.util.List;

public class FragmentHistorico extends Fragment implements HistoricoView{
    static String TAG = "FragmentHistorico";

    HistoricoPresenter historicoPresenter;


    HistoricoAdapter historicoAdapter;

    MainActivity activity;
    ListView lstHistoricoLocacoes;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_historico_locacao, container, false);

        activity = (MainActivity) getActivity();

        lstHistoricoLocacoes = v.findViewById(R.id.lstHistoricoLocacoes);

        historicoAdapter = new HistoricoAdapter(getContext(), activity);

        lstHistoricoLocacoes.setAdapter(historicoAdapter);

        historicoPresenter = new HistoricoPresenter(this, ServiceFactory.create());



        historicoPresenter.listarHistorico(activity.idUsuarioLogado);




        return v;
    }

    @Override
    public void sucesso(List<VHistorico_Locacao> historico_locacoes) {
        historicoAdapter.clear();
        historicoAdapter.addAll(historico_locacoes);
    }
}
