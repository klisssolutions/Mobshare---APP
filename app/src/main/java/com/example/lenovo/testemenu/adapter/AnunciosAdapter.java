package com.example.lenovo.testemenu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.testemenu.MainActivity;
import com.example.lenovo.testemenu.R;
import com.example.lenovo.testemenu.fragment.FragmentSemResultado;
import com.example.lenovo.testemenu.fragment.FragmentVeiculoDados;
import com.example.lenovo.testemenu.model.AnuncioResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AnunciosAdapter extends ArrayAdapter<AnuncioResult> {

    MainActivity activity;

    //criando a lista dos anuncios
    public AnunciosAdapter(Context ctx){
        super(ctx, 0, new ArrayList<AnuncioResult>());

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if (v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.veiculo_layout, parent, false);
        }

        final AnuncioResult anuncioResult = getItem(position);




        //instanciando e já preenchendo com os dados
        TextView txtModelo = v.findViewById(R.id.txtModelo);
        TextView txtMarca = v.findViewById(R.id.txtMarca);
        ImageView imgVeiculo = v.findViewById(R.id.imgVeiculo);

        txtModelo.setText(anuncioResult.getNomeModelo());
        txtMarca.setText(anuncioResult.getNomeMarca());

        CardView cardVeiculo = v.findViewById(R.id.cardVeiculo);

//        cardVeiculo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                activity = new MainActivity();
////                FragmentManager fm = activity.getSupportFragmentManager();
////                Fragment fragmentVeiculoDados = new FragmentVeiculoDados();
////                fm.beginTransaction().replace(R.id.frameLayout, fragmentVeiculoDados).commit();
//
//                Log.d("stop", anuncioResult.getIdVeiculo()+"");
//            }
//        });

        String urlImagem = "http:10.0.2.2/Mobshare/arquivos/"+anuncioResult.getFotoVeiculo();

        Picasso.get().load(urlImagem).into(imgVeiculo);



        return v;
    }
}
