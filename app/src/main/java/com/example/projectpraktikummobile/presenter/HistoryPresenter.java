package com.example.projectpraktikummobile.presenter;

import android.os.AsyncTask;

import com.example.projectpraktikummobile.contact.HistoryContact;
import com.example.projectpraktikummobile.entity.AppDatabase;
import com.example.projectpraktikummobile.entity.DataHistory;

import java.util.List;

public class HistoryPresenter implements HistoryContact.presenter {

    private HistoryContact.view view;

    public HistoryPresenter(HistoryContact.view view) {
        this.view = view;
    }

    class InsertData extends AsyncTask<Void,Void,Long>{
        private AppDatabase appDatabase;
        private DataHistory dataHistory;

        public InsertData(AppDatabase appDatabase,DataHistory dataHistory){
            this.appDatabase = appDatabase;
            this.dataHistory = dataHistory;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return appDatabase.dao().insertData(dataHistory);
        }

        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successAdd();
        }

    }

    @Override
    public void insertData(String idKotaAsal, String namaKotaAsal, String idKotaTujuan, String namaKotaTujuan, int berat, String kurir, AppDatabase database) {
        final DataHistory dataHistory = new DataHistory();
        dataHistory.setIdKotaAsal(idKotaAsal);
        dataHistory.setIdKotaTujuan(idKotaTujuan);
        dataHistory.setNamaKotaAsal(namaKotaAsal);
        dataHistory.setNamaKotaTujuan(namaKotaTujuan);
        dataHistory.setBerat(berat);
        dataHistory.setKurir(kurir);

        new InsertData(database,dataHistory).execute();
    }

    @Override
    public void readData(AppDatabase database) {
        List<DataHistory> list;
        list = database.dao().getData();
        view.getData(list);
    }

    class DeleteData extends AsyncTask<Void,Void,Long>{
        private AppDatabase appDatabase;
        private DataHistory dataHistory;

        public DeleteData(AppDatabase appDatabase,DataHistory dataHistory){
            this.appDatabase = appDatabase;
            this.dataHistory = dataHistory;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            appDatabase.dao().deleteData(dataHistory);
            return null;
        }

        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successDelete();
        }

    }

    @Override
    public void deleteData(DataHistory dataHistory, AppDatabase database) {
        new DeleteData(database,dataHistory).execute();
    }
}
