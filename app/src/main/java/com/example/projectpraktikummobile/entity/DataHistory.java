package com.example.projectpraktikummobile.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "history_db")
public class DataHistory {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "namaKotaAsal")
    private String namaKotaAsal;
    @ColumnInfo(name = "idKotaAsal")
    private String idKotaAsal;
    @ColumnInfo(name = "namaKotaTujuan")
    private String namaKotaTujuan;
    @ColumnInfo(name = "idKotaTujuan")
    private String idKotaTujuan;
    @ColumnInfo(name = "berat")
    private int berat;
    @ColumnInfo(name = "kurir")
    private String kurir;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaKotaAsal() {
        return namaKotaAsal;
    }

    public void setNamaKotaAsal(String namaKotaAsal) {
        this.namaKotaAsal = namaKotaAsal;
    }

    public String getIdKotaAsal() {
        return idKotaAsal;
    }

    public void setIdKotaAsal(String idKotaAsal) {
        this.idKotaAsal = idKotaAsal;
    }

    public String getNamaKotaTujuan() {
        return namaKotaTujuan;
    }

    public void setNamaKotaTujuan(String namaKotaTujuan) {
        this.namaKotaTujuan = namaKotaTujuan;
    }

    public String getIdKotaTujuan() {
        return idKotaTujuan;
    }

    public void setIdKotaTujuan(String idKotaTujuan) {
        this.idKotaTujuan = idKotaTujuan;
    }

    public int getBerat() {
        return berat;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }

    public String getKurir() {
        return kurir;
    }

    public void setKurir(String kurir) {
        this.kurir = kurir;
    }
}
