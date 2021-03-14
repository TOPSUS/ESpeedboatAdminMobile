package com.example.espeedboatadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transaksi {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("asal")
    @Expose
    private String asal;
    @SerializedName("tujuan")
    @Expose
    private String tujuan;
    @SerializedName("tanggal_berangkat")
    @Expose
    private String tanggalBerangkat;
    @SerializedName("tanggal_sampai")
    @Expose
    private String tanggalSampai;
    @SerializedName("jam_berangkat")
    @Expose
    private String jamBerangkat;
    @SerializedName("jam_sampai")
    @Expose
    private String jamSampai;
    @SerializedName("person")
    @Expose
    private Integer person;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getTanggalBerangkat() {
        return tanggalBerangkat;
    }

    public void setTanggalBerangkat(String tanggalBerangkat) {
        this.tanggalBerangkat = tanggalBerangkat;
    }

    public String getTanggalSampai() {
        return tanggalSampai;
    }

    public void setTanggalSampai(String tanggalSampai) {
        this.tanggalSampai = tanggalSampai;
    }

    public String getJamBerangkat() {
        return jamBerangkat;
    }

    public void setJamBerangkat(String jamBerangkat) {
        this.jamBerangkat = jamBerangkat;
    }

    public String getJamSampai() {
        return jamSampai;
    }

    public void setJamSampai(String jamSampai) {
        this.jamSampai = jamSampai;
    }

    public Integer getPerson() {
        return person;
    }

    public void setPerson(Integer person) {
        this.person = person;
    }

}
