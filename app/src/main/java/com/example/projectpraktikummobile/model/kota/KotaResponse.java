package com.example.projectpraktikummobile.model.kota;

import com.google.gson.annotations.SerializedName;

public class KotaResponse{

	@SerializedName("rajaongkir")
	private Rajaongkir rajaongkir;

	public void setRajaongkir(Rajaongkir rajaongkir){
		this.rajaongkir = rajaongkir;
	}

	public Rajaongkir getRajaongkir(){
		return rajaongkir;
	}

	@Override
 	public String toString(){
		return 
			"KotaResponse{" + 
			"rajaongkir = '" + rajaongkir + '\'' + 
			"}";
		}
}