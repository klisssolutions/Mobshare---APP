package com.example.lenovo.testemenu.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.lenovo.testemenu.R;
import com.example.lenovo.testemenu.model.Cliente;
import com.example.lenovo.testemenu.presenter.CadastroClientePresenter;
import com.example.lenovo.testemenu.service.ServiceFactory;
import com.example.lenovo.testemenu.view.CadastroView;

public class FragmentCadastro extends Fragment implements CadastroView {
    static String TAG = "FragmentCadastro";

    Button btnCadastrar;
    EditText edtnome;
    EditText edtcpf;
    EditText edtcnh;
    EditText edtemail;
    EditText edtsenha;
    EditText edtcategoriaCNH;


    FragmentManager fm;

    CadastroClientePresenter ccPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cadastro, container, false);


        fm = getActivity().getSupportFragmentManager();


        edtnome = v.findViewById(R.id.edtCadNome);
        edtemail = v.findViewById(R.id.edtCadEmail);
        edtsenha = v.findViewById(R.id.edtCadSenha);
        edtcnh = v.findViewById(R.id.edtCadCNH);
        edtcategoriaCNH = v.findViewById(R.id.edtCadCategoriaCNH);
        edtcpf = v.findViewById(R.id.edtCadCPF);

        ccPresenter = new CadastroClientePresenter(ServiceFactory.create(), this);

        btnCadastrar = v.findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Cliente c = new Cliente();
                c.setNome(edtnome.getText().toString());
                c.setCategoriaCnh(edtcategoriaCNH.getText().toString());
                c.setCnh(edtcnh.getText().toString());
                c.setCpf(edtcpf.getText().toString());
                c.setDataCadastro("2019-04-02");
                c.setEmail(edtemail.getText().toString());
                c.setSenha(edtsenha.getText().toString());

                ccPresenter.chamarCadastrar(c);


            }
        });

        return v;


    }

    @Override
    public void sucesso() {
        alert("Cadastro", "Cadastro efetuado com sucesso", "Logar");
    }

    @Override
    public void fracasso() {
        Log.e("leo", "a");
    }

    @Override
    public void semConexao() {
        Log.e("leo", "noconex");
    }


    public void alert(String titulo, String mensagem, String positive) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setMessage(mensagem);
        alertDialogBuilder.setPositiveButton(positive, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                Log.d("leo", "menosmal");

                Fragment fragmentLogin = new FragmentLogin();
                fm.beginTransaction().replace(R.id.frameLayout, fragmentLogin).commit();
            }
        });


        alertDialogBuilder.show();
    }
}

