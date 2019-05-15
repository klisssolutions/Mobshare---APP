package com.example.lenovo.testemenu.service;

import com.example.lenovo.testemenu.model.AnuncioResult;
import com.example.lenovo.testemenu.model.ApiResult;
import com.example.lenovo.testemenu.model.LoginResult;
import com.example.lenovo.testemenu.model.Marca;
import com.example.lenovo.testemenu.model.Modelo;
import com.example.lenovo.testemenu.model.VSolicitacaoLocacao;
import com.example.lenovo.testemenu.model.Veiculo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ClienteService {
    String URL_BASE = "http://10.0.2.2/Mobshare/API/"; //trecho sujeito a erro


    //@Field para passar via POST

    //@Query para montar a url no estilo PHP ex: exemplo.php?modo=logar

    //@Path para montar a URL estilo NODE ex:
    // @GET("/usuario/{id}")
    //    Call<Usuario> obterUsuarioPorid(@Path("id") int id);


    @FormUrlEncoded
    @POST("cliente.php?modo=logar")
    Call<LoginResult> logar(@Field("txtemail") String email, @Field("txtsenha") String senha);
    //Call<ApiResult> logar(@Field("txtemail") String email, @Field("txtsenha") String senha, @Query("modo") String modo);

    @FormUrlEncoded
    @POST("cliente.php?modo=inserir")
    Call<ApiResult> inserir(@Field("txtnome") String nome, @Field("txtcpf") String cpf, @Field("txtcnh") String cnh,
                            @Field("txtcategoriacnh") String categoriacnh, @Field("txtemail") String email,
                            @Field("txtsenha") String senha, @Field("txtdatacadastro") String datacadastro);


    @POST("anuncios_mobile.php?modo=lista")
    Call<List<AnuncioResult>> obterAnuncios();

    @GET("marca.php?modo=lista")
    Call<List<Marca>>  obterMarcas();

    @GET("modelo.php?modo=lista")
    Call<List<Modelo>>  obterModelos();

//    @GET("anuncios_mobile.php")
//    Call<List<AnuncioResult>>  filtrarAnuncios(@Query("modo") String modo   , @Query("cbMarca") int idMarca, @Query("cbModelo") int idModelo,
//                                                @Query("cbKM") String KM, @Query("cbAvaliacao") int avaliacao);

    @GET("anuncios_mobile.php")
    Call<List<AnuncioResult>>  filtrarAnuncios(@Query("modo") String modo   , @Query("cbMarca") int idMarca, @Query("cbModelo") int idModelo,
                                               @Query("cbKM") String KM, @Query("cbAvaliacao") String avaliacao,
                                               @Query("cbTipoVeiculo") int idTipoVeiculo);

    @GET("anuncios_mobile.php")
    Call<Veiculo>  buscarVeiculo(@Query("modo") String modo, @Query("id") int id);

    @GET("locacao.php")
    Call<ApiResult>  inserirSolicitacao_Locacao(@Query("modo") String modo, @Query("idCliente") int idCliente,
                                              @Query("idVeiculo") int idVeiculo, @Query("txtHorarioInicio") String horarioInicio,
                                              @Query("txtHorarioFim") String horarioFim);

    @GET("anuncios_mobile.php")
    Call<List<VSolicitacaoLocacao>>  listarSolicitacoes();




}
