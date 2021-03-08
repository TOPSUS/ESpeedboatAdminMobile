package com.example.espeedboatadmin.model;

public class Review {
    private String review, username;
    private int score, id_speedboat, id_pembelian, id_review;

    public Review (String review, String username, int score, int id_speedboat, int id_pembelian) {
        this.username = username;
        this.review = review;
        this.score = score;
        this.id_speedboat = id_speedboat;
        this.id_pembelian = id_pembelian;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
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
