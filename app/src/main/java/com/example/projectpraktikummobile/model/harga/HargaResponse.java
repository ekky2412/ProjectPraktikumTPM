package com.example.projectpraktikummobile.model.harga;

import com.google.gson.annotations.SerializedName;

public class HargaResponse{

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
			"HargaResponse{" + 
			"rajaongkir = '" + rajaongkir + '\'' + 
			"}";
		}
}