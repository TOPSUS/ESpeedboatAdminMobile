package com.example.espeedboatadmin.adapter;

import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.espeedboatadmin.R;
import com.example.espeedboatadmin.model.Review;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    private ArrayList<Review> dataList;
    private ImageView iv;

    public ReviewAdapter(ArrayList<Review> list) {
        this.dataList = list;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        iv = new ImageView(parent.getContext());
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        int score = 0;

        score = dataList.get(position).getScore();
        LinearLayout.LayoutParams lp_score = new LinearLayout.LayoutParams(
                                                LinearLayout.LayoutParams.MATCH_PARENT,
                                                LinearLayout.LayoutParams.WRAP_CONTENT
                                            );
//        ImageView images[]
//        for (int i=0; i < score; i++) {
//            lp_score.setMargins(2, 0, 2, 0);
//
//        }
//        holder.score.addView(star_view);

        holder.username.setText(dataList.get(position).getUsername());
        holder.id_pembelian.setText("ORDER20200309");
//        holder.score.setText(String.valueOf(dataList.get(position).getScore()));
        holder.review.setText(dataList.get(position).getReview());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder{
        private TextView username, id_pembelian, review;
        private LinearLayout score;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.review_text_username);
            id_pembelian = (TextView) itemView.findViewById(R.id.review_text_id_pembelian);
            score = (LinearLayout) itemView.findViewById(R.id.score_wrapper);
            review = (TextView) itemView.findViewById(R.id.review_text_review);
        }
    }
}
