package com.example.lenovo.testemenu.service;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    public static ClienteService create(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ClienteService.URL_BASE).addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit.create(ClienteService.class);
    }

}
