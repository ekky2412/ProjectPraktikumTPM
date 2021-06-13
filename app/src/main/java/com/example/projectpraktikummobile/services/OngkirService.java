package com.example.projectpraktikummobile.services;

import android.util.Log;

import com.example.projectpraktikummobile.model.harga.CostsItem;
import com.example.projectpraktikummobile.model.harga.HargaResponse;
import com.example.projectpraktikummobile.model.kota.KotaResponse;
import com.example.projectpraktikummobile.model.kota.ResultsItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OngkirService {
    private Retrofit retrofit = null;

    public OngkirAPI getOngkirAPI(){
        if(retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(OngkirAPI.URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(OngkirAPI.class);
    }

//    Request API Kota
    public void getKotaAPI(final OngkirListener<List<ResultsItem>> listener){
        getOngkirAPI().getKota().enqueue(new Callback<KotaResponse>() {
            @Override
            public void onResponse(Call<KotaResponse> call, Response<KotaResponse> response) {
                KotaResponse data = response.body();
                if(data!=null && data.getRajaongkir()!=null){

                    listener.onResponse(data.getRajaongkir().getResults());

                }
            }

            @Override
            public void onFailure(Call<KotaResponse> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }

        });
    }

//    Request API Harga

    public void getHargaAPI(final OngkirListener<List<CostsItem>> listener, String origin, String destination, int weight, String courier){
        getOngkirAPI().getHarga(origin,destination,weight,courier).enqueue(new Callback<HargaResponse>() {
            @Override
            public void onResponse(Call<HargaResponse> call, Response<HargaResponse> response) {
                HargaResponse data = response.body();
                if(data!=null && data.getRajaongkir()!=null){
                    listener.onResponse(data.getRajaongkir().getResults().get(0).getCosts());
                    Log.d("hasil2", String.valueOf(data.getRajaongkir().getResults().get(0).getCode()));
                }

            }

            @Override
            public void onFailure(Call<HargaResponse> call, Throwable t) {
                listener.onFailure(t.getMessage());
                Log.d("pesan error",t.getMessage());
            }
        });
    }
}
