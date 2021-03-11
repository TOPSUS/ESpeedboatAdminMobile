package com.example.espeedboatadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Total {
    @SerializedName("total_review")
    @Expose
    private Integer totalReview;
    @SerializedName("total_score")
    @Expose
    private Integer totalScore;
    @SerializedName("score")
    @Expose
    private Score score;

    public Integer getTotalReview() {
        return totalReview;
    }

    public void setTotalReview(Integer totalReview) {
        this.totalReview = totalReview;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
