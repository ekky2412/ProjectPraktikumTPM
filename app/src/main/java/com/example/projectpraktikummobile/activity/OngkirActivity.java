package com.example.projectpraktikummobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.projectpraktikummobile.R;
import com.example.projectpraktikummobile.R;
import com.example.projectpraktikummobile.adapter.AdapterOngkir;
import com.example.projectpraktikummobile.model.harga.CostsItem;
import com.example.projectpraktikummobile.services.OngkirListener;
import com.example.projectpraktikummobile.services.OngkirService;

import java.util.List;

public class OngkirActivity extends AppCompatActivity {

    TextView tv_kotaAsal,tv_kotaTujuan,tv_kurir,tv_berat;

    String idKotaAsal,idKotaTujuan,NamaKotaAsal,NamaKotaTujuan,berat,kurir;

    RecyclerView rvItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongkir);

        tv_kotaAsal = findViewById(R.id.tv_kotaAsal);
        tv_kotaTujuan = findViewById(R.id.tv_kotaTujuan);
        tv_berat = findViewById(R.id.tv_berat);
        tv_kurir = findViewById(R.id.tv_kurir);
        rvItem = findViewById(R.id.ongkir_rv);

        idKotaAsal = getIntent().getStringExtra("idKotaAsal");
        idKotaTujuan = getIntent().getStringExtra("idKotaTujuan");
        NamaKotaAsal = getIntent().getStringExtra("NamaKotaAsal");
        NamaKotaTujuan = getIntent().getStringExtra("NamaKotaTujuan");
        berat = getIntent().getStringExtra("berat");
        kurir = getIntent().getStringExtra("kurir");

        tv_kotaAsal.setText(NamaKotaAsal);
        tv_kotaTujuan.setText(NamaKotaTujuan);
        tv_kurir.setText(kurir);
        tv_berat.setText(berat + " gram");

        new OngkirService().getHargaAPI(hargaListener,idKotaAsal,idKotaTujuan, Integer.parseInt(berat),kurir);

    }

    OngkirListener<List<CostsItem>> hargaListener = new OngkirListener<List<CostsItem>>() {
        @Override
        public void onResponse(List<CostsItem> items) {
            final LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext());
            layout.setOrientation(LinearLayoutManager.VERTICAL);
            rvItem.setLayoutManager(layout);

            Log.d("hasil", String.valueOf(items));

            AdapterOngkir adapter = new AdapterOngkir(items);
            rvItem.setAdapter(adapter);

        }

        @Override
        public void onFailure(String message) {
            Log.d("pesan","gagal ges");
        }
    };

}