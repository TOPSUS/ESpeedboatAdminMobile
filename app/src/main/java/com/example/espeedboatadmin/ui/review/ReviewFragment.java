package com.example.espeedboatadmin.ui.review;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.espeedboatadmin.R;
import com.example.espeedboatadmin.model.Data;
import com.example.espeedboatadmin.model.ReviewList;
import com.example.espeedboatadmin.model.ReviewSummary;
import com.example.espeedboatadmin.model.ReviewSummaryScore;
import com.example.espeedboatadmin.utils.Utils;
import com.example.espeedboatadmin.adapter.ReviewAdapter;
import com.example.espeedboatadmin.client.RetrofitClient;
import com.example.espeedboatadmin.client.SessionManager;
import com.example.espeedboatadmin.model.Response;
import com.example.espeedboatadmin.service.ReviewService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ReviewFragment extends Fragment {

    private RecyclerView recyclerView;
    private View view;
    private RecyclerView.LayoutManager layoutManager;
    private ReviewService service;
    private int transaksi_id = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.transaksi_id = getArguments().getInt(Utils.TRANSAKSI_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_review, container, false);
        recyclerView = view.findViewById(R.id.recycle_review);
        service = RetrofitClient.getClient().create(ReviewService.class);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        getData();

        return view;
    }

    private void getData() {
        Call<Response> getReviews = service.getReviews(new SessionManager().getAuthToken(), transaksi_id);

        getReviews.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NotNull Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 200) {
                        Data data = response.body().getData();
                        ReviewSummary summary = data.getReviewSummary();

                        List<ReviewList> reviewList = data.getReviewList();
                        int totalScore = summary.getTotalScore();
                        int totalReview = summary.getTotalReview();
                        ReviewSummaryScore reviewSummaryScoreReview = summary.getReviewSummaryScore();

                        recyclerView.setAdapter(new ReviewAdapter(reviewList));
                        setAllReview(reviewSummaryScoreReview, totalScore, totalReview);
                        Toast.makeText(getActivity(), response.body().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), response.body().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e("ERROR [ReviewFragment] ", t.getMessage());
                Toast.makeText(getActivity(),  t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setAllReview(ReviewSummaryScore reviewSummaryScore, int tscore, int treview) {
        TextView text_score = view.findViewById(R.id.total_score);
        TextView text_review = view.findViewById(R.id.total_review);
        LinearLayout score_wrapper = view.findViewById(R.id.total_score_wrapper);
        ProgressBar pb_1 = view.findViewById(R.id.review_progress_1);
        ProgressBar pb_2 = view.findViewById(R.id.review_progress_2);
        ProgressBar pb_3 = view.findViewById(R.id.review_progress_3);
        ProgressBar pb_4 = view.findViewById(R.id.review_progress_4);
        ProgressBar pb_5 = view.findViewById(R.id.review_progress_5);

        text_score.setText(String.valueOf(tscore));
        text_review.setText(String.valueOf(treview) + " ReviewList");

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                Utils.dpToPx(16, view.getContext()),
                Utils.dpToPx(16, view.getContext())
        );

        for(int i=0; i < 5; i++) {
            ImageView imageView = new ImageView(view.getContext());
            imageView.setLayoutParams(params);
            if (i < tscore) {
                imageView.setImageResource(R.drawable.ic_full_star);
            } else {
                imageView.setImageResource(R.drawable.ic_stroke_star);
            }
            score_wrapper.addView(imageView);
        }

        pb_1.setProgress(reviewSummaryScore.get1() * 100/treview);
        pb_2.setProgress(reviewSummaryScore.get2() * 100/treview);
        pb_3.setProgress(reviewSummaryScore.get3() * 100/treview);
        pb_4.setProgress(reviewSummaryScore.get4() * 100/treview);
        pb_5.setProgress(reviewSummaryScore.get5() * 100/treview);
    }
}