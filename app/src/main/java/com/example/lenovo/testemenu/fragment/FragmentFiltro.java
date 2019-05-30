package com.example.lenovo.testemenu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.example.lenovo.testemenu.MainActivity;
import com.example.lenovo.testemenu.R;
import com.example.lenovo.testemenu.model.AnuncioResult;
import com.example.lenovo.testemenu.model.Filtro;
import com.example.lenovo.testemenu.model.KM;
import com.example.lenovo.testemenu.model.Marca;
import com.example.lenovo.testemenu.model.Modelo;
import com.example.lenovo.testemenu.presenter.MarcaPresenter;
import com.example.lenovo.testemenu.presenter.ModeloPresenter;
import com.example.lenovo.testemenu.presenter.PresenterCidade;
import com.example.lenovo.testemenu.presenter.PresenterFiltro;
import com.example.lenovo.testemenu.presenter.PresenterUF;
import com.example.lenovo.testemenu.service.ServiceFactory;
import com.example.lenovo.testemenu.view.AnunciosView;
import com.example.lenovo.testemenu.view.FiltroView;
import com.example.lenovo.testemenu.view.MarcaView;
import com.example.lenovo.testemenu.view.ModeloView;
import com.example.lenovo.testemenu.view.ViewAnunciosFiltro;
import com.example.lenovo.testemenu.view.ViewCidade;
import com.example.lenovo.testemenu.view.ViewUF;

import java.util.ArrayList;
import java.util.List;

public class FragmentFiltro extends Fragment implements MarcaView, ModeloView, ViewUF, ViewCidade {
    public static String TAG = "FragmentFiltro";

    ViewAnunciosFiltro viewAnunciosFiltro;

    MainActivity activity;

    Filtro filtro;
    Button btnFiltrar;

    Spinner cbMarca;
    Spinner cbModelo;
    Spinner cbKM;
    Spinner cbUF;
    Spinner cbCidade;
    RatingBar rbAvaliacao;
    ImageView imgCarro;
    ImageView imgMoto;
    ImageView imgBicicleta;

    String UFS[];
    String cidades[];


    KM km;
    List<KM> kmList;
    List<Marca> marcas;
    List<Modelo> modelos;


    //PresenterFiltro presenterFiltro;
    MarcaPresenter marcaPresenter;
    ModeloPresenter modeloPresenter;
    PresenterUF presenterUF;
    PresenterCidade presenterCidade;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_filtro, container, false);

        filtro = new Filtro();

        activity = (MainActivity) getActivity();

        btnFiltrar = v.findViewById(R.id.btnFiltrar);
        cbMarca  = v.findViewById(R.id.cbMarca);
        cbModelo  = v.findViewById(R.id.cbModelo);
        cbUF  = v.findViewById(R.id.cbUF);
        cbCidade = v.findViewById(R.id.cbCidade);
        rbAvaliacao= v.findViewById(R.id.rbAvaliacao);

        imgBicicleta = v.findViewById(R.id.imgBicicleta);
        imgMoto = v.findViewById(R.id.imgMoto);
        imgCarro = v.findViewById(R.id.imgCarro);

        filtro.setIdTipoVeiculo(0);

        imgBicicleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtro.setIdTipoVeiculo(3);
            }
        });



        imgMoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtro.setIdTipoVeiculo(2);
            }
        });

        imgCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtro.setIdTipoVeiculo(1);
            }
        });

        cbKM  = v.findViewById(R.id.cbKm);

        kmList = new ArrayList<>();

        marcaPresenter = new MarcaPresenter(this, ServiceFactory.create());
        modeloPresenter = new ModeloPresenter(this, ServiceFactory.create());
        presenterUF = new PresenterUF(ServiceFactory.create(), this);


        filtro.setAvaliacao("Selecione");
        carregarCbKM();

        //presenterFiltro = new PresenterFiltro(this, ServiceFactory.create());


        rbAvaliacao.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                filtro.setAvaliacao(rating+"");
            }
        });

        btnFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                FragmentAnuncios fragmentAnuncios = new FragmentAnuncios();

                Bundle bundle = new Bundle();
                bundle.putInt("idMarca", filtro.getIdMarca());
                bundle.putInt("idModelo", filtro.getIdModelo());
                bundle.putString("km", filtro.getKm()+"");
                bundle.putString("avaliacao", filtro.getAvaliacao());
                bundle.putInt("idTipoVeiculo", filtro.getIdTipoVeiculo());
                bundle.putString("UF", filtro.getUF());
                bundle.putString("cidade", filtro.getCidade());

                fragmentAnuncios.setArguments(bundle);

                activity.navegar(fragmentAnuncios, fragmentAnuncios.TAG);






            }
        });

        cbModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtro.setIdModelo(modelos.get(position).getIdModelo());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        cbKM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtro.setKm(kmList.get(position).getKm());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cbMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtro.setIdMarca(marcas.get(position).getIdMarca());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cbUF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                filtro.setUF(UFS[position]);
                presenterCidade = new PresenterCidade(ServiceFactory.create(), FragmentFiltro.this);

                presenterCidade.carregarCidades(UFS[position]);//passo a uf selecionada para puxar as cidades daquela UF
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cbCidade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("beijo", cidades[position]);
                filtro.setCidade(cidades[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return v;
    }

    public void carregarCbKM(){

        KM km1 = new KM("Selecione", "padrao");
        KM km2 = new KM("Abaixo de 10.000KM", "10000");
        KM km3 = new KM("Abaixo de 50.000KM", "50000");
        KM km4 = new KM("Abaixo de 100.000KM", "100000");
        KM km5 = new KM("Abaixo de 400.000KM", "400000");
        KM km6 = new KM("Acima de 500.000KM", "500000");



        kmList.add(km1);
        kmList.add(km2);
        kmList.add(km3);
        kmList.add(km4);
        kmList.add(km5);
        kmList.add(km6);


        ArrayAdapter<KM> adapterKM = new ArrayAdapter<KM>(getContext(), android.R.layout.simple_spinner_item, kmList);
        adapterKM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cbKM.setAdapter(adapterKM);
    }


    @Override
    public void onResume() {
        super.onResume();
        marcaPresenter.carregarMarcas();
        modeloPresenter.carregarModelos();
        presenterUF.carregarUF();


    }


    @Override
    public void sucessoMarca(List<Marca> marcas) {
        this.marcas = marcas;

        ArrayAdapter<Marca> adapterMarca = new ArrayAdapter<Marca>(getContext(), android.R.layout.simple_spinner_item, this.marcas);
        adapterMarca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cbMarca.setAdapter(adapterMarca);
    }



    @Override
    public void semConexaoMarca() {

    }

    @Override
    public void sucessoModelo(List<Modelo> modelos) {
        this.modelos = modelos;

        ArrayAdapter<Modelo> adapterModelo = new ArrayAdapter<Modelo>(getContext(), android.R.layout.simple_spinner_item, this.modelos);
        adapterModelo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        cbModelo.setAdapter(adapterModelo);

    }

    @Override
    public void semConexaoModelo() {

    }


    @Override
    public void sucessoUF(String[] UFS) {

        this.UFS = UFS;
        ArrayAdapter<String> adapterUF = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, this.UFS);
        adapterUF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cbUF.setAdapter(adapterUF);
    }

    @Override
    public void fracassoUF() {

    }

    @Override
    public void sucessoCidade(String[] cidades) {
        this.cidades = cidades;
        ArrayAdapter<String> adapterCidade = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, this.cidades);
        adapterCidade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cbCidade.setAdapter(adapterCidade);
    }

    @Override
    public void fracassoCidade() {

    }
}
