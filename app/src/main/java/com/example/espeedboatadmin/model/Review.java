package com.example.espeedboatadmin.model;

public class Review {
    private String review;
    private int score, id_speedboat, id_pembelian, id_review;

    public Review (String review, int score, int id_speedboat, int id_pembelian) {
        this.review = review;
        this.score = score;
        this.id_speedboat = id_speedboat;
        this.id_pembelian = id_pembelian;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReview() {
        return review;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setId_speedboat(int id_speedboat) {
        this.id_speedboat = id_speedboat;
    }

    public int getId_speedboat() {
        return id_speedboat;
    }

    public void setId_pembelian(int id_pembelian) {
        this.id_pembelian = id_pembelian;
    }

    public int getId_pembelian() {
        return id_pembelian;
    }
}
