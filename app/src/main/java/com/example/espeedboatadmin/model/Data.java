package com.example.espeedboatadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("review_list")
    @Expose
    private List<ReviewList> reviewList = null;
    @SerializedName("review_summary")
    @Expose
    private ReviewSummary reviewSummary;
    @SerializedName("review_detail")
    @Expose
    private ReviewDetail reviewDetail;
    @SerializedName("review_detail_order")
    @Expose
    private ReviewDetailOrder reviewDetailOrder;
    @SerializedName("transaksi")
    @Expose
    private List<Transaksi> transaksi = null;

    public List<ReviewList> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<ReviewList> reviewList) {
        this.reviewList = reviewList;
    }

    public ReviewSummary getReviewSummary() {
        return reviewSummary;
    }

    public void setReviewSummary(ReviewSummary reviewSummary) {
        this.reviewSummary = reviewSummary;
    }
    public ReviewDetail getReviewDetail() {
        return reviewDetail;
    }

    public void setReviewDetail(ReviewDetail reviewDetail) {
        this.reviewDetail = reviewDetail;
    }

    public ReviewDetailOrder getReviewDetailOrder() {
        return reviewDetailOrder;
    }

    public void setReviewDetailOrder(ReviewDetailOrder reviewDetailOrder) {
        this.reviewDetailOrder = reviewDetailOrder;
    }

    public List<Transaksi> getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(List<Transaksi> transaksi) {
        this.transaksi = transaksi;
    }
}
