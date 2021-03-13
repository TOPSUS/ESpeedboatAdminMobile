package com.example.espeedboatadmin.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.espeedboatadmin.R;
import com.example.espeedboatadmin.holder.ReviewViewHolder;
import com.example.espeedboatadmin.model.ReviewList;
import com.example.espeedboatadmin.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {
    private final List<ReviewList> dataList;

    public ReviewAdapter(List<ReviewList> list) {
        this.dataList = list;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_review, parent, false);

        return new ReviewViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        setData(holder, dataList.get(position));
        holder.itemView.setOnClickListener(v -> { itemClickListener(dataList.get(position)); });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    private void setData(@NonNull ReviewViewHolder holder, ReviewList data) {
        holder.setEmail(data.getEmail());
        holder.setTanggal(data.getTanggal());
        holder.setReview(data.getReview());
        holder.setScoreText(data.getScore().toString());
        holder.setScore(data.getScore());
        holder.setImageUser(data.getImage());
    }

    private void itemClickListener(ReviewList item) {
        Log.d("LISTENER [REVIEW]", item.getId().toString());
    }
}
