package com.example.lenovo.testemenu;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.lenovo.testemenu.fragment.FragmentAnuncios;
import com.example.lenovo.testemenu.fragment.FragmentCadastro;
import com.example.lenovo.testemenu.fragment.FragmentLogin;
import com.example.lenovo.testemenu.model.AnuncioResult;

import com.example.lenovo.testemenu.view.ViewAnunciosFiltro;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ViewAnunciosFiltro {

    FragmentManager fm;
    public TextView txtNomeLogin;
    public int idUsuarioLogado = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fm = getSupportFragmentManager();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState();




        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);

        txtNomeLogin = (TextView) headerView.findViewById(R.id.txtNomeLogin);



        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.frameLayout, new FragmentLogin());

        ft.commit();


        navigationView.setNavigationItemSelectedListener(this);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.itemLocalizacao) {
            // Handle the camera action
        } else if (id == R.id.itemCadastro) {

            FragmentTransaction ft = fm.beginTransaction();//responsavel por fazer a transação da fragments

            ft.replace(R.id.frameLayout, new FragmentCadastro());

            ft.commit(); //envia as alterações
        } else if (id == R.id.itemLogin) {

            FragmentTransaction ft = fm.beginTransaction();

            ft.replace(R.id.frameLayout, new FragmentLogin());

            ft.commit();

        } else if (id == R.id.itemBusca) {
            FragmentTransaction ft = fm.beginTransaction();

            ft.replace(R.id.frameLayout, new FragmentAnuncios());

            ft.commit();
        } else if (id == R.id.itemSair) {

            idUsuarioLogado = 0;

            txtNomeLogin.setText("Faça seu login!");

            FragmentTransaction ft = fm.beginTransaction();

            ft.replace(R.id.frameLayout, new FragmentLogin());

            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void passarAnuncios(List<AnuncioResult> anuncios) {
        Fragment fragmentAnuncios = new FragmentAnuncios();

//        Bundle bundle = new Bundle();
//        bundle.putString("username", "oi");
//
//        fragmentAnuncios.setArguments(bundle);
//
//        FragmentManager fm = getSupportFragmentManager();
//        fm.beginTransaction().replace(R.id.frameLayout, fragmentAnuncios).commit();

       // ((FragmentAnuncios) fragmentAnuncios).preencherLista(anuncios);



    }


    public void navegar(Fragment fragment, String tag) {
        FragmentTransaction t = fm.beginTransaction();
        t.addToBackStack(tag);
        t.replace(R.id.frameLayout, fragment).commit();
    }



}
