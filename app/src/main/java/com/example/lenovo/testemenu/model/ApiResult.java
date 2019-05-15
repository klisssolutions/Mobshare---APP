package com.example.lenovo.testemenu.model;

import com.google.gson.annotations.SerializedName;

public class ApiResult {
    //@SerializedName("id") se usa essa notacao quando o atributo nao tem o mesmo nome de retorno daa api
    @SerializedName("status")
    private boolean erro;


    public boolean isErro() {
        return erro;
    }

    public void setErro(boolean erro) {
        this.erro = erro;
    }
}
