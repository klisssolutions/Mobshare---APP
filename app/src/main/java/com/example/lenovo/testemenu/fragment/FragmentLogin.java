package com.example.lenovo.testemenu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lenovo.testemenu.MainActivity;
import com.example.lenovo.testemenu.R;
import com.example.lenovo.testemenu.presenter.LoginPresenter;
import com.example.lenovo.testemenu.service.ClienteService;
import com.example.lenovo.testemenu.service.ServiceFactory;
import com.example.lenovo.testemenu.view.LoginView;

public class FragmentLogin extends Fragment implements LoginView {
    static String TAG = "FragmentLogin";

    Button btnEntrar;
    EditText edtEmail;
    EditText edtSenha;
    TextView txtLinkCadastro;
    TextView txtUSIncorreto;

    public MenuItem itemHistorico;
    public MenuItem itemLogin;
    public MenuItem itemCadastro;
    public MenuItem itemSair;




    ClienteService cService;
    LoginPresenter lPresenter;



    MainActivity activity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        btnEntrar = v.findViewById(R.id.btnEntrar);
        edtEmail = v.findViewById(R.id.edtEmail);
        edtSenha = v.findViewById(R.id.edtSenha);
        txtLinkCadastro = v.findViewById(R.id.txtCadastro);
        txtUSIncorreto = v.findViewById(R.id.txtUSincorreto);

        activity = (MainActivity) getActivity() ;





        txtLinkCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentCadastro fragmentCadastro = new FragmentCadastro();
                activity.navegar(fragmentCadastro, fragmentCadastro.TAG);

            }
        });

        lPresenter = new LoginPresenter(ServiceFactory.create(), this);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String senha = edtSenha.getText().toString();

                lPresenter.mandarParaLogin(email, senha);

                ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(edtSenha.getWindowToken(), 0);

            }
        });




        return v;
    }

    @Override
    public void logou(String nomeCliente, int idCliente) {
        activity.idUsuarioLogado = idCliente;
        Log.e("doido" , idCliente+"");
        txtUSIncorreto.setVisibility(View.INVISIBLE);
        activity.txtNomeLogin.setText("Bem vindo "+nomeCliente+"!");


        NavigationView navigationView = (NavigationView) activity.findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);




        Menu menuNav = navigationView.getMenu();

        itemHistorico = menuNav.findItem(R.id.itemHistorico);
        itemCadastro = menuNav.findItem(R.id.itemCadastro);
        itemLogin = menuNav.findItem(R.id.itemLogin);
        itemSair = menuNav.findItem(R.id.itemSair);



        itemCadastro.setVisible(false);
        itemLogin.setVisible(false);
        itemHistorico.setVisible(true);
        itemSair.setVisible(true);

        FragmentAnuncios fragmentAnuncios = new FragmentAnuncios();

        activity.navegar(fragmentAnuncios, fragmentAnuncios.TAG);

    }

    @Override
    public void naoLogou() {
        txtUSIncorreto.setVisibility(View.VISIBLE);
    }

    @Override
    public void semConexao() {
        FragmentSemConexao fragmentSemConexao = new FragmentSemConexao();

        activity.navegar(fragmentSemConexao, fragmentSemConexao.TAG);


    }
}
