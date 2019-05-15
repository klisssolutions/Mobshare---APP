package com.example.lenovo.testemenu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.testemenu.MainActivity;
import com.example.lenovo.testemenu.R;
import com.example.lenovo.testemenu.model.Veiculo;
import com.example.lenovo.testemenu.presenter.HistoricoPresenter;
import com.example.lenovo.testemenu.view.HistoricoView;

public class FragmentHistorico extends Fragment implements HistoricoView{
    static String TAG = "FragmentHistorico";

    HistoricoPresenter historicoPresenter;

    Veiculo veiculo;

    MainActivity activity;

    TextView txtNomeModelo;
    TextView txtNomeMarca;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_veiculos_dados, container, false);




        activity = (MainActivity) getActivity();

        

        txtNomeModelo = v.findViewById(R.id.txtModelo);
        txtNomeMarca = v.findViewById(R.id.txtMarca);





        return v;
    }

    @Override
    public void sucesso() {

    }
}
