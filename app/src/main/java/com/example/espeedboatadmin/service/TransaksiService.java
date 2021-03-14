package com.example.espeedboatadmin.service;

import com.example.espeedboatadmin.client.Endpoint;
import com.example.espeedboatadmin.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface TransaksiService {
    @GET(Endpoint.TRANSAKSI_LIST_PROSES)
    Call<Response> getTransaksiProses(@Header("Authorization") String token);

    @GET(Endpoint.TRANSAKSI_LIST_SELESAI)
    Call<Response> getTransaksiSelesai(@Header("Authorization") String token);
}
