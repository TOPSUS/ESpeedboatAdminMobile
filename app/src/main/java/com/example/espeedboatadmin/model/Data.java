package com.example.espeedboatadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("auth")
    @Expose
    private Auth auth;
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
    @SerializedName("transaksi_list")
    @Expose
    private List<Transaksi> transaksiLists = null;
    @SerializedName("transaksi")
    @Expose
    private Transaksi transaksi = null;
    @SerializedName("tiket")
    @Expose
    private Tiket tiket;

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

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

    public List<Transaksi> getTransaksiList() {
        return transaksiLists;
    }

    public void setTransaksiList(List<Transaksi> transaksi) {
        this.transaksiLists = transaksi;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public Tiket getTiket() {
        return tiket;
    }

    public void setTiket(Tiket tiket) {
        this.tiket = tiket;
    }

}
