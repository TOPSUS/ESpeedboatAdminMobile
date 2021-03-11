package com.example.espeedboatadmin.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.espeedboatadmin.R;

public class ReviewViewHolder extends RecyclerView.ViewHolder {
    private TextView username, id_pembelian, review;
    private LinearLayout score;

    public ReviewViewHolder(@NonNull View itemView) {
        super(itemView);
        getIds();
    }

    public void getIds() {
        username = (TextView) itemView.findViewById(R.id.review_text_username);
        id_pembelian = (TextView) itemView.findViewById(R.id.review_text_id_pembelian);
        score = (LinearLayout) itemView.findViewById(R.id.score_wrapper);
        review = (TextView) itemView.findViewById(R.id.review_text_review);
    }

    public TextView getUsername() {
        return username;
    }

    public TextView getId_pembelian() {
        return id_pembelian;
    }

    public TextView getReview() {
        return review;
    }

    public LinearLayout getScore() {
        return score;
    }
}
