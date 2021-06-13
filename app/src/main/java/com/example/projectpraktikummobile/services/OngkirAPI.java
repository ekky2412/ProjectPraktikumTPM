package com.example.projectpraktikummobile.services;

import com.example.projectpraktikummobile.model.harga.HargaResponse;
import com.example.projectpraktikummobile.model.kota.KotaResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface OngkirAPI {
    String URL_BASE = "https://api.rajaongkir.com";

    @Headers({
            "key: cbd7906bc55b64329040ad60e33c889e",
            "content-type: application/x-www-form-urlencoded"
    })
    @GET("/starter/city")
    Call<KotaResponse> getKota();

    @Headers({
            "key: cbd7906bc55b64329040ad60e33c889e",
            "content-type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("/starter/cost")
    Call<HargaResponse> getHarga(@Field("origin") String origin,
                                 @Field("destination") String destination,
                                 @Field("weight") int weight,
                                 @Field("courier") String courier);
}
