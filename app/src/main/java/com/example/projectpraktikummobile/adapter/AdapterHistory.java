package com.example.projectpraktikummobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectpraktikummobile.R;
import com.example.projectpraktikummobile.activity.OngkirActivity;
import com.example.projectpraktikummobile.contact.HistoryContact;
import com.example.projectpraktikummobile.entity.DataHistory;

import java.util.List;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.viewHolder> {
    Context context;
    List<DataHistory> list;
    HistoryContact.view mView;

    public AdapterHistory(Context context, List<DataHistory> list, HistoryContact.view mView) {
        this.context = context;
        this.list = list;
        this.mView = mView;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_history,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final DataHistory item = list.get(position);
        holder.tv_berat.setText(item.getBerat() + "gram");
        holder.tv_namaKotaTujuan.setText(item.getNamaKotaTujuan());
        holder.tv_namaKotaAsal.setText(item.getNamaKotaAsal());
        holder.tv_kurir.setText(item.getKurir());
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.deleteData(item);
            }
        });
        holder.btn_detail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), OngkirActivity.class);
                intent.putExtra("NamaKotaAsal",item.getNamaKotaAsal());
                intent.putExtra("NamaKotaTujuan",item.getNamaKotaTujuan());
                intent.putExtra("idKotaAsal",item.getIdKotaAsal());
                intent.putExtra("idKotaTujuan",item.getIdKotaTujuan());
                intent.putExtra("berat",item.getBerat());
                intent.putExtra("kurir",item.getKurir());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tv_namaKotaAsal,tv_namaKotaTujuan,tv_berat,tv_kurir;
        Button btn_detail;
        AppCompatButton btn_delete;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            tv_berat = itemView.findViewById(R.id.tvHistory_berat);
            tv_kurir = itemView.findViewById(R.id.tvHistory_kurir);
            tv_namaKotaAsal = itemView.findViewById(R.id.tvHistory_kotaAsal);
            tv_namaKotaTujuan = itemView.findViewById(R.id.tvHistory_kotaTujuan);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            btn_detail = itemView.findViewById(R.id.btn_detail);
        }
    }
}
