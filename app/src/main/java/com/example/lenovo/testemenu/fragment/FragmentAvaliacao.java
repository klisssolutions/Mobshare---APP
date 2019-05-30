package com.example.lenovo.testemenu.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.lenovo.testemenu.MainActivity;
import com.example.lenovo.testemenu.R;
import com.example.lenovo.testemenu.model.Avaliacao;
import com.example.lenovo.testemenu.presenter.AvaliacaoPresenter;
import com.example.lenovo.testemenu.service.ServiceFactory;
import com.example.lenovo.testemenu.view.AvaliacaoView;


public class FragmentAvaliacao extends Fragment implements AvaliacaoView {
    public static String TAG = "FragmentAvaliacao";

    Button btnAvaliar;
    EditText edDepoimento;
    RatingBar rbAvaliacao;
    Avaliacao avaliacao;

    MainActivity activity;

    Bundle bundle;

    AvaliacaoPresenter avaliacaoPresenter;

    int idLocacao;
    String tipoAvaliacao;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_avaliacao, container, false);
        bundle = getArguments();
        if(bundle != null){
            idLocacao = bundle.getInt("idLocacao");
            tipoAvaliacao = bundle.getString("tipoAvaliacao");
        }


        activity = (MainActivity) getActivity();
        btnAvaliar = v.findViewById(R.id.btnAvaliar);
        edDepoimento = v.findViewById(R.id.edDepoimento);
        rbAvaliacao = v.findViewById(R.id.rbAvaliacao);



        btnAvaliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avaliacao = new Avaliacao();
                avaliacao.setDepoimento(edDepoimento.getText().toString());
                avaliacao.setIdLocacao(idLocacao);
                avaliacao.setNota(rbAvaliacao.getNumStars());

                avaliacaoPresenter = new AvaliacaoPresenter(FragmentAvaliacao.this, ServiceFactory.create());

                avaliacaoPresenter.avaliar(avaliacao, tipoAvaliacao);
            }
        });



        return v;
    }

    @Override
    public void sucessoAvaliacao() {
        alert("Obrigado!", "Sua avaliação foi registrada com sucesso!", "OK");
    }

    @Override
    public void fracassoAvaliacao() {

    }


    public void alert(String titulo, String mensagem, String positive) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setMessage(mensagem);
        alertDialogBuilder.setPositiveButton(positive, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                FragmentHistorico fragmentHistorico = new FragmentHistorico();
                activity.navegar(fragmentHistorico, fragmentHistorico.TAG);
            }
        });


        alertDialogBuilder.show();
    }

}
