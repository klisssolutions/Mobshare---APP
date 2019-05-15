package com.example.lenovo.testemenu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.testemenu.MainActivity;
import com.example.lenovo.testemenu.R;
import com.example.lenovo.testemenu.model.SolicitacaoLocacao;
import com.example.lenovo.testemenu.model.Veiculo;
import com.example.lenovo.testemenu.presenter.VeiculoPresenter;
import com.example.lenovo.testemenu.service.ClienteService;
import com.example.lenovo.testemenu.service.ServiceFactory;
import com.example.lenovo.testemenu.view.VeiculoView;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;

public class FragmentVeiculoDados extends Fragment implements VeiculoView {
    static String TAG = "FragmentVeiculoDados";

    VeiculoPresenter veiculoPresenter;


    Veiculo veiculo;

    int idVeiculo;

    MainActivity activity;

    TextView txtNomeModelo;
    TextView txtNomeMarca;
    TextView txtValor;
    TextView txtAno;
    TextView txtNota;
    TextView txtAcessorio;

    FloatingTextButton btnInteresse;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_veiculos_dados, container, false);

        Bundle bundle = getArguments();




        txtNomeModelo = v.findViewById(R.id.txtModelo);
        txtNomeMarca = v.findViewById(R.id.txtMarca);
        txtValor = v.findViewById(R.id.txtValor);
        txtAno = v.findViewById(R.id.txtAno);
        txtNota = v.findViewById(R.id.txtNota);
        txtAcessorio = v.findViewById(R.id.txtAcessorio);
        btnInteresse = v.findViewById(R.id.btnInteresse);

        activity = (MainActivity) getActivity();

        if(bundle != null){
            idVeiculo = bundle.getInt("idVeiculo");

            veiculoPresenter = new VeiculoPresenter(this, ServiceFactory.create());

            veiculoPresenter.buscarVeiculo(idVeiculo);

        }

        btnInteresse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsolicitacaoLocacao fragmentsolicitacaoLocacao = new FragmentsolicitacaoLocacao();

                Bundle b = new Bundle();
                b.putInt("idVeiculo", idVeiculo);

                fragmentsolicitacaoLocacao.setArguments(b);

                activity.navegar(fragmentsolicitacaoLocacao, fragmentsolicitacaoLocacao.TAG);

                Log.d("usuario", activity.idUsuarioLogado+"");
                Log.d("veiculo", veiculo.getIdVeiculo()+"");
            }
        });

        return v;
    }

    @Override
    public void sucessoVeiculo(Veiculo v) {
        veiculo = v;

        txtNomeMarca.setText(veiculo.getNomeMarca());
        txtNomeModelo.setText(veiculo.getNomeModelo());
        txtAno.setText("Ano "+veiculo.getAno());
        txtNota.setText(veiculo.getNota()+"/5");
        txtValor.setText("R$"+veiculo.getValor()+"/h");

        String acessorios="";
        if(veiculo.getAcessorios().size() == 1){
            acessorios = "Contém "+veiculo.getAcessorios().get(0);

        }else if(veiculo.getAcessorios().size() == 2){
            acessorios = "Contém "+veiculo.getAcessorios().get(0);
            acessorios = acessorios+", "+veiculo.getAcessorios().get(1);

        }else if(veiculo.getAcessorios().size() > 3){
            acessorios = "Contém "+veiculo.getAcessorios().get(0);

            for(int i = 0; i<veiculo.getAcessorios().size()-1; i++){
                acessorios = acessorios+", "+veiculo.getAcessorios().get(1);
            }
            acessorios = acessorios+", "+veiculo.getAcessorios().get(veiculo.getAcessorios().size() - 1);
        }


        txtAcessorio.setText(acessorios);

    }


}
