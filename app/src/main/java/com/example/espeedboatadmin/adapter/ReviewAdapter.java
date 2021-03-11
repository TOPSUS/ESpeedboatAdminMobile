package com.example.espeedboatadmin.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.espeedboatadmin.R;
import com.example.espeedboatadmin.holder.ReviewViewHolder;
import com.example.espeedboatadmin.model.Review;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {
    private final ArrayList<Review> dataList;
//    private ImageView iv;

    public ReviewAdapter(ArrayList<Review> list) {
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
//        int score = 0;

//        score = dataList.get(position).getScore();
//        LinearLayout.LayoutParams lp_score = new LinearLayout.LayoutParams(
//                                                LinearLayout.LayoutParams.MATCH_PARENT,
//                                                LinearLayout.LayoutParams.WRAP_CONTENT
//                                            );
//        ImageView images[]
//        for (int i=0; i < score; i++) {
//            lp_score.setMargins(2, 0, 2, 0);
//        }
//        holder.score.addView(star_view);

        holder.getUsername().setText(dataList.get(position).getUsername());
        holder.getId_pembelian().setText("ORDER20200309");
        holder.getReview().setText(dataList.get(position).getReview());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }
}
