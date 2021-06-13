package com.example.projectpraktikummobile.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectpraktikummobile.R;
import com.example.projectpraktikummobile.adapter.AdapterHistory;
import com.example.projectpraktikummobile.contact.HistoryContact;
import com.example.projectpraktikummobile.entity.AppDatabase;
import com.example.projectpraktikummobile.entity.DataHistory;
import com.example.projectpraktikummobile.presenter.HistoryPresenter;

import java.util.List;

public class HistoryFragment extends Fragment implements HistoryContact.view {

    RecyclerView history_rv;
    Button btn_delete;
    AppCompatButton btn_detail;
    TextView tv_kotaAsal,tv_kotaTujuan,tv_berat,tv_kurir;

    AppDatabase appDatabase;
    HistoryPresenter historyPresenter;
    AdapterHistory adapterHistory;

    private int id = 0;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);

        history_rv = view.findViewById(R.id.history_rv);
        tv_kotaAsal = view.findViewById(R.id.tvHistory_kotaAsal);
        tv_kotaTujuan = view.findViewById(R.id.tvHistory_kotaTujuan);
        tv_berat = view.findViewById(R.id.tvHistory_berat);
        tv_kurir = view.findViewById(R.id.tvHistory_kurir);
        btn_delete = view.findViewById(R.id.btn_delete);
        btn_detail = view.findViewById(R.id.btn_detail);

        // Koneksi ke DB
        appDatabase = AppDatabase.inidb(view.getContext());
        // Masuk ke RV
        history_rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        historyPresenter = new HistoryPresenter(this);
        historyPresenter.readData(appDatabase);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void successAdd() {

    }

    @Override
    public void successDelete() {
        Toast.makeText(getContext(),"Berhasil menghapus data",Toast.LENGTH_SHORT).show();
        historyPresenter.readData(appDatabase);
    }

    @Override
    public void getData(List<DataHistory> list) {
        adapterHistory = new AdapterHistory(getContext(),list,this);
        history_rv.setAdapter(adapterHistory);
    }

    @Override
    public void deleteData(DataHistory item) {
        AlertDialog.Builder builder;
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            builder = new AlertDialog.Builder(getContext(),
                    android.R.style.Theme_Material_Dialog_Alert);
        }else{
            builder = new AlertDialog.Builder(getContext());
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Anda yakin ingin menghapus data ini?")
                .setPositiveButton(android.R.string.yes, new
                        DialogInterface.OnClickListener() { //kalau diklik yes
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                historyPresenter.deleteData(item,appDatabase);
                                //proses hapus data
                            }
                        })
                .setNegativeButton(android.R.string.no, new
                        DialogInterface.OnClickListener() { //kalau diklik no
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                .setIcon(android.R.drawable.ic_dialog_dialer)
                .show();
    }

    @Override
    public void onClick(View v) {

    }
}