package com.example.projectpraktikummobile.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.projectpraktikummobile.R;
import com.example.projectpraktikummobile.activity.OngkirActivity;
import com.example.projectpraktikummobile.model.kota.ResultsItem;
import com.example.projectpraktikummobile.services.OngkirListener;
import com.example.projectpraktikummobile.services.OngkirService;

import java.util.ArrayList;
import java.util.List;

public class CekOngkirFragment extends Fragment {

    Spinner spinner_kotaAsal,spinner_kotaTujuan,spinner_kurir;
    EditText et_berat;
    AppCompatButton btn_submit;

    String selectedNamaKotaAsal = "", selectedNamaKotaTujuan = "";
    String selectedIdKotaAsal = "",selectedIdKotaTujuan = "",selectedKurir = "";
    List<String> listIdKota = new ArrayList<String>();
    List<String> listSpinnerKotaAsal = new ArrayList<String>();
    List<String> listSpinnerKotaTujuan = new ArrayList<String>();
    List<String> listKurir = new ArrayList<String>();

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        btn_submit = view.findViewById(R.id.btn_submit);
        et_berat = view.findViewById(R.id.et_berat);
        spinner_kotaAsal = view.findViewById(R.id.spinner_kotaAsal);
        spinner_kotaTujuan = view.findViewById(R.id.spinner_kotaTujuan);
        spinner_kurir = view.findViewById(R.id.spinner_kurir);

//        spinner_kotaAsal.setAdapter();

        new OngkirService().getKotaAPI(kotaListener);


        spinner_kotaAsal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedNamaKotaAsal = parent.getItemAtPosition(position).toString();
                selectedIdKotaAsal = listIdKota.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_kotaTujuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedNamaKotaTujuan = parent.getItemAtPosition(position).toString();
                selectedIdKotaTujuan = listIdKota.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_kurir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedKurir = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(et_berat.getText().toString())){
                    et_berat.setError("Berat tidak boleh kosong!");
                }
                else{
                    Intent intent = new Intent(getContext(), OngkirActivity.class);
                    intent.putExtra("NamaKotaAsal",selectedNamaKotaAsal);
                    intent.putExtra("NamaKotaTujuan",selectedNamaKotaTujuan);
                    intent.putExtra("idKotaAsal",selectedIdKotaAsal);
                    intent.putExtra("idKotaTujuan",selectedIdKotaTujuan);
                    intent.putExtra("berat",et_berat.getText().toString());
                    intent.putExtra("kurir",selectedKurir);
                    startActivity(intent);
//                    finish();
                }
            }

        });

    }

    OngkirListener<List<ResultsItem>> kotaListener = new OngkirListener<List<ResultsItem>>() {
        @Override
        public void onResponse(List<ResultsItem> items) {

            for(int i=0;i<items.size();i++){
                listSpinnerKotaAsal.add(items.get(i).getCityName());
                listSpinnerKotaTujuan.add(items.get(i).getCityName());
                listIdKota.add(items.get(i).getCityId());
            }

            listKurir.add("jne");
            listKurir.add("pos");
            listKurir.add("tiki");

            ArrayAdapter<String> adapterKotaAsal = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,listSpinnerKotaAsal);
            adapterKotaAsal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_kotaAsal.setAdapter(adapterKotaAsal);

            ArrayAdapter<String> adapterKotaTujuan = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,listSpinnerKotaTujuan);
            adapterKotaTujuan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_kotaTujuan.setAdapter(adapterKotaTujuan);

            ArrayAdapter<String> adapterKurir = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,listKurir);
            adapterKurir.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_kurir.setAdapter(adapterKurir);

        }

        @Override
        public void onFailure(String message) {
            Log.d("isi Error : ", message);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cek_ongkir, container, false);
    }
}