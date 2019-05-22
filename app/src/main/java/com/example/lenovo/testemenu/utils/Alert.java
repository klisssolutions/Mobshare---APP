package com.example.lenovo.testemenu.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.lenovo.testemenu.R;
import com.example.lenovo.testemenu.fragment.FragmentLogin;

public class Alert {

    Context context;

    public  Alert(Context context){
        this.context = context;
    }


    public void alert(String titulo, String mensagem, String positive) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setMessage(mensagem);
        alertDialogBuilder.setPositiveButton(positive, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

            }
        });


        alertDialogBuilder.show();
    }
}
