package com.example.lenovo.testemenu.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.lenovo.testemenu.MainActivity;
import com.example.lenovo.testemenu.R;
import com.example.lenovo.testemenu.model.SolicitacaoLocacao;
import com.example.lenovo.testemenu.presenter.SolicitacaoLocacaoPresenter;
import com.example.lenovo.testemenu.service.ServiceFactory;
import com.example.lenovo.testemenu.view.ViewSolicitacaoLocacao;

import java.util.Calendar;

public class FragmentsolicitacaoLocacao extends Fragment implements DatePickerDialog.OnDateSetListener,  TimePickerDialog.OnTimeSetListener, ViewSolicitacaoLocacao{

    static String TAG = "FragmentsolicitacaoLocacao";
    SolicitacaoLocacao solicitacaoLocacao;

    Boolean flag_inicio; // variavel responsavel por controlar pickerdata e time serão para inicio ou fim, se verdadeiro é para o inicio, se false, para o fim

    Button btnSolicitar;

    MainActivity activity;

    SolicitacaoLocacaoPresenter solicitacaoLocacaoPresenter;

    TextView txtInicio;
    TextView txtFim;
    Button btnInicio;
    Button btnFim;
    String horarioInicio;
    String horarioFim;

    int idVeiculoSelecionado;


    DatePickerDialog.OnDateSetListener mDataSetListener;
    TimePickerDialog.OnTimeSetListener mTimeSetListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_solicitacao_locacao, container, false);

        solicitacaoLocacao = new SolicitacaoLocacao();

        Bundle bundle = getArguments();
        if(bundle != null){
            solicitacaoLocacao.setIdVeiculo(bundle.getInt("idVeiculo"));

        }
        Log.d("aiai", bundle.getInt("idVeiculo")+"");



        activity = (MainActivity) getActivity();


        btnSolicitar = v.findViewById(R.id.btnSolicitar);
        txtInicio = v.findViewById(R.id.txtInicio);
        txtFim = v.findViewById(R.id.txtFim);
        btnInicio = v.findViewById(R.id.btnInicio);
        btnFim = v.findViewById(R.id.btnFim);


        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag_inicio = true;
                Calendar cal = Calendar.getInstance();
                int ano = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(),
                        FragmentsolicitacaoLocacao.this, ano, mes, dia);

                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();



            }
        });

        btnFim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag_inicio = false;
                Calendar cal = Calendar.getInstance();
                int ano = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(),
                        FragmentsolicitacaoLocacao.this, ano, mes, dia);

                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        btnSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activity.idUsuarioLogado != 0){

                    solicitacaoLocacao.setIdCliente(activity.idUsuarioLogado);

                    solicitacaoLocacaoPresenter = new SolicitacaoLocacaoPresenter(ServiceFactory.create(), FragmentsolicitacaoLocacao.this);

                    solicitacaoLocacaoPresenter.solicitarLocacao(solicitacaoLocacao);

                }else{
                    FragmentLogin fragmentLogin = new FragmentLogin();

                    activity.navegar(fragmentLogin, fragmentLogin.TAG);
                }

            }
        });


        return v;
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


        if(flag_inicio){
            horarioInicio = dayOfMonth+"/"+(month+1) +"/"+year;
            solicitacaoLocacao.setHorarioInicio(year+"-"+(month+1)+"-"+dayOfMonth);
            txtInicio.setText(horarioInicio);
        }else{
            horarioFim = dayOfMonth+"/"+(month+1) +"/"+year;
            solicitacaoLocacao.setHorarioFim(year+"-"+(month+1)+"-"+dayOfMonth);
            txtFim.setText(horarioFim);
        }


        Calendar c = Calendar.getInstance();
        int hora = c.get(Calendar.HOUR);
        int minuto = c.get(Calendar.MINUTE);




        TimePickerDialog dialogTime = new TimePickerDialog(getContext(),
                this, hora, minuto, true);

        //dialogTime.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogTime.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if(flag_inicio){
            horarioInicio = horarioInicio + " - "+hourOfDay+":"+minute;
            solicitacaoLocacao.setHorarioInicio(solicitacaoLocacao.getHorarioInicio()+" "+hourOfDay+":"+minute);
            Log.d("inicio", solicitacaoLocacao.getHorarioInicio());
            txtInicio.setText(horarioInicio);
        }else{
            horarioFim = horarioFim + " - "+hourOfDay+":"+minute;
            solicitacaoLocacao.setHorarioFim(solicitacaoLocacao.getHorarioFim()+" "+hourOfDay+":"+minute);
            Log.d("fim", solicitacaoLocacao.getHorarioFim());
            txtFim.setText(horarioFim);
        }

    }

    @Override
    public void sucesso() {

    }

    @Override
    public void fracasso() {

    }
}
