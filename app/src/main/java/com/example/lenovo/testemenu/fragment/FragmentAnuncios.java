package com.example.lenovo.testemenu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lenovo.testemenu.MainActivity;
import com.example.lenovo.testemenu.R;
import com.example.lenovo.testemenu.adapter.AnunciosAdapter;
import com.example.lenovo.testemenu.model.AnuncioResult;
import com.example.lenovo.testemenu.model.Filtro;
import com.example.lenovo.testemenu.presenter.AnunciosPresenter;
import com.example.lenovo.testemenu.presenter.PresenterFiltro;
import com.example.lenovo.testemenu.service.ServiceFactory;
import com.example.lenovo.testemenu.view.AnunciosView;
import com.example.lenovo.testemenu.view.FiltroView;

import java.util.List;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;

public class FragmentAnuncios extends Fragment implements AnunciosView, FiltroView {
    static String TAG = "FragmentAnuncios";

    AnunciosAdapter anunciosAdapter;

    ListView lstAnuncios;
    AnunciosPresenter anunciosPresenter;
    PresenterFiltro presenterFiltro;
    String ai;
    Filtro filtro;

    MainActivity activity;

    FloatingTextButton txtFiltro;
    //TextView txtFiltro;




    public FragmentAnuncios(){}
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            filtro = new Filtro();
            filtro.setIdMarca(bundle.getInt("idMarca"));
            Log.d("marca", filtro.getIdMarca()+"");
            filtro.setIdModelo(bundle.getInt("idModelo"));

            Log.d("modelo", filtro.getIdModelo()+"");
            filtro.setKm(bundle.getString("km"));
            Log.d("km", filtro.getKm()+"");
            filtro.setAvaliacao(bundle.getString("avaliacao"));
            Log.d("avaliacao", filtro.getAvaliacao()+"");
            filtro.setIdTipoVeiculo(bundle.getInt("idTipoVeiculo"));
            Log.d("avaliacao", filtro.getAvaliacao()+"");
            presenterFiltro = new PresenterFiltro(this, ServiceFactory.create());

            presenterFiltro.carregarAnuncios(filtro);



        }else{
            anunciosPresenter.carregarAnuncios();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.veiculos_fragment, container, false);

        activity = (MainActivity) getActivity();

        txtFiltro = v.findViewById(R.id.txtFiltro);
        txtFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentFiltro fragmentFiltro = new FragmentFiltro();
                activity.navegar(fragmentFiltro, fragmentFiltro.TAG);
            }
        });

        lstAnuncios = v.findViewById(R.id.lstAnuncios);
        anunciosAdapter = new AnunciosAdapter(getContext());
        lstAnuncios.setAdapter(anunciosAdapter);



        lstAnuncios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AnuncioResult a = new AnuncioResult();
                a = anunciosAdapter.getItem(position);

                Bundle b = new Bundle();
                b.putInt("idVeiculo", a.getIdVeiculo());

                FragmentVeiculoDados fragmentVeiculoDados = new FragmentVeiculoDados();

                fragmentVeiculoDados.setArguments(b);

                activity.navegar(fragmentVeiculoDados, fragmentVeiculoDados.TAG);


            }
        });




        anunciosPresenter = new AnunciosPresenter(this, ServiceFactory.create());


//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, marcas);
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, marcas);



        return v;
    }



    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void sucessoAnuncio(List<AnuncioResult> lstAnuncios) {
        anunciosAdapter.clear();
        anunciosAdapter.addAll(lstAnuncios);
    }


    @Override
    public void sucessoFiltro(List<AnuncioResult> anuncios) {
        Log.d("tamanho", anuncios.size()+"");
        anunciosAdapter.clear();
        anunciosAdapter.addAll(anuncios);
    }

    @Override
    public void pesquisaVazia() {
        anunciosAdapter.clear();
        FragmentSemResultado fragmentSemResultado = new FragmentSemResultado();
        activity.navegar(fragmentSemResultado, fragmentSemResultado.TAG);
    }

    @Override
    public void semConexaoFiltro() {

    }
}
