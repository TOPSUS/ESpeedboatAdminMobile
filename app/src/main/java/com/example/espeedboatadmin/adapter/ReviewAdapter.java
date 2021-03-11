package com.example.espeedboatadmin.adapter;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.espeedboatadmin.R;
import com.example.espeedboatadmin.holder.ReviewViewHolder;
import com.example.espeedboatadmin.model.ResponseReview;
import com.example.espeedboatadmin.model.Review;
import com.example.espeedboatadmin.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {
    private final List<Review> dataList;

    public ReviewAdapter(List<Review> list) {
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
        holder.getEmail().setText(dataList.get(position).getEmail());
        holder.getTanggal().setText(dataList.get(position).getTanggal());
        holder.getReview().setText(dataList.get(position).getReview());
        holder.getScoreText().setText(dataList.get(position).getScore().toString());
        holder.setScore(dataList.get(position).getScore());
        Picasso.get().load(dataList.get(position).getImage()).transform(new CircleTransform()).into(holder.getImageUser());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }
}
