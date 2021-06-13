package com.example.projectpraktikummobile.contact;

import android.view.View;

import com.example.projectpraktikummobile.entity.AppDatabase;
import com.example.projectpraktikummobile.entity.DataHistory;

import java.util.List;

public interface HistoryContact {
    interface view extends View.OnClickListener{
        void successAdd();
        void successDelete();
        void getData(List<DataHistory> list);
        void deleteData(DataHistory item);
    }

    interface presenter{
        void insertData(String idKotaAsal,String namaKotaAsal,String idKotaTujuan,String namaKotaTujuan,int berat,String kurir, AppDatabase database);
        void readData(AppDatabase database);
        void deleteData(DataHistory dataHistory, AppDatabase database);
    }
}
