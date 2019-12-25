package com.example.maehendra14.percobaan_sertifikasi;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RvKegiatanAdapter extends RecyclerView.Adapter<RvKegiatanAdapter.KegiatanHolder> {

    ArrayList<KegiatanModel> listKegiatan;

    public RvKegiatanAdapter(ArrayList<KegiatanModel> listKegiatan) {
        this.listKegiatan = listKegiatan;
    }

    @NonNull
    @Override
    public KegiatanHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_kegiatan,viewGroup,false);
        return new KegiatanHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KegiatanHolder kegiatanHolder, int i) {
        KegiatanModel k = listKegiatan.get(i);
        kegiatanHolder.tvNama.setText(k.getNama());
        kegiatanHolder.tvKeterangan.setText(k.getKeterangan());
        kegiatanHolder.tvWaktu.setText(k.getWaktu());
    }

    @Override
    public int getItemCount() {
        return listKegiatan.size();
    }

    public class KegiatanHolder extends RecyclerView.ViewHolder {
        TextView tvNama,tvKeterangan,tvWaktu;
        public KegiatanHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvKeterangan = itemView.findViewById(R.id.tvKeterangan);
            tvWaktu = itemView.findViewById(R.id.tvWaktu);
        }
    }
}
