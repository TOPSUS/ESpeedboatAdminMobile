package com.example.espeedboatadmin.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.espeedboatadmin.R;
import com.example.espeedboatadmin.holder.TransaksiViewHolder;
import com.example.espeedboatadmin.model.Transaksi;

import java.util.List;


public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiViewHolder>{
    private final List<Transaksi> dataList;

    public TransaksiAdapter (List<Transaksi> list) {
        this.dataList = list;
    }

    @NonNull
    @Override
    public TransaksiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_transaksi, parent, false);

        return new TransaksiViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TransaksiViewHolder holder, int position) {
        setData(holder, dataList.get(position));
        Log.d("DEBUG [TransaksiAdapter]", dataList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public void setData(@NonNull TransaksiViewHolder holder, Transaksi item) {
        holder.setUsername(item.getUsername());
        holder.setEmail(item.getEmail());
        holder.setOrderDate(item.getTanggal());
        holder.setPerson(item.getPerson() + " Persons");
        holder.setStatus(item.getStatus());
        holder.setFrom(item.getAsal());
        holder.setFromDate(item.getTanggalBerangkat());
        holder.setFromTime(item.getJamBerangkat());
        holder.setTo(item.getTujuan());
        holder.setToDate(item.getTanggalSampai());
        holder.setToTime(item.getJamSampai());
    }
}
