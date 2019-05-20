package com.example.lenovo.testemenu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lenovo.testemenu.MainActivity;
import com.example.lenovo.testemenu.R;
import com.example.lenovo.testemenu.adapter.HistoricoAdapter;
import com.example.lenovo.testemenu.model.Veiculo;
import com.example.lenovo.testemenu.presenter.HistoricoPresenter;
import com.example.lenovo.testemenu.view.HistoricoView;

public class FragmentHistorico extends Fragment implements HistoricoView{
    static String TAG = "FragmentHistorico";

    HistoricoPresenter historicoPresenter;

    Veiculo veiculo;
    HistoricoAdapter historicoAdapter;

    MainActivity activity;
    ListView lstHistoricoLocacoes;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_historico_locacao, container, false);

        lstHistoricoLocacoes = v.findViewById(R.id.lstHistoricoLocacoes);

        lstHistoricoLocacoes.setAdapter(historicoAdapter);



        activity = (MainActivity) getActivity();


        return v;
    }

    @Override
    public void sucesso() {

    }
}
