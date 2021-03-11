package com.example.espeedboatadmin.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.espeedboatadmin.R;
import com.example.espeedboatadmin.utils.Utils;

public class ReviewViewHolder extends RecyclerView.ViewHolder {
    private TextView email, tanggal, review, score_text;
    private LinearLayout score;
    private ImageView imageUser;

    public ReviewViewHolder(@NonNull View itemView) {
        super(itemView);
        getIds();
    }

    public void getIds() {
        email = (TextView) itemView.findViewById(R.id.review_text_email);
        tanggal = (TextView) itemView.findViewById(R.id.review_text_tanggal);
        score = (LinearLayout) itemView.findViewById(R.id.score_wrapper);
        score_text = (TextView) itemView.findViewById(R.id.review_text_score);
        review = (TextView) itemView.findViewById(R.id.review_text_review);
        imageUser = (ImageView) itemView.findViewById(R.id.image_user);
    }

    public TextView getEmail() {
        return email;
    }

    public TextView getTanggal() { return tanggal; }

    public TextView getReview() {
        return review;
    }

    public TextView getScoreText() {
        return score_text;
    }

    public LinearLayout getScore() {
        return score;
    }

    public void setScore(int value) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                Utils.dpToPx(16, itemView.getContext()),
                Utils.dpToPx(16, itemView.getContext())
        );

        for(int i=0; i < 5; i++) {
            ImageView imageView = new ImageView(itemView.getContext());
            imageView.setLayoutParams(params);
            if (i < value) {
                imageView.setImageResource(R.drawable.ic_full_star);
            } else {
                imageView.setImageResource(R.drawable.ic_stroke_star);
            }
            score.addView(imageView);
        }
    }

    public ImageView getImageUser() {
        return imageUser;
    }
}
