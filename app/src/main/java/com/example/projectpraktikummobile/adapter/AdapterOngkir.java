package com.example.projectpraktikummobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectpraktikummobile.R;
import com.example.projectpraktikummobile.model.harga.CostsItem;

import java.util.List;

public class AdapterOngkir extends RecyclerView.Adapter<AdapterOngkir.ViewHolder> {
    List<CostsItem> items;

    public AdapterOngkir(List<CostsItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public AdapterOngkir.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemsRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service,parent,false);
        return new AdapterOngkir.ViewHolder(itemsRow);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOngkir.ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_tipeService,tv_tipeServiceDesc,tv_estimasi,tv_harga;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_tipeService = itemView.findViewById(R.id.tv_tipeService);
            tv_tipeServiceDesc = itemView.findViewById(R.id.tv_tipeServiceDesc);
            tv_estimasi = itemView.findViewById(R.id.tv_estimasi);
            tv_harga = itemView.findViewById(R.id.tv_harga);

        }

        public void bind(CostsItem items){
            tv_tipeService.setText(items.getService());
            tv_tipeServiceDesc.setText(items.getDescription());
            tv_estimasi.setText(items.getCost().get(0).getEtd() + " hari");
            tv_harga.setText("Rp " + items.getCost().get(0).getValue());
        }
    }
}
