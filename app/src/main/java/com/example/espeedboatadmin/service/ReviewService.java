package com.example.espeedboatadmin.service;

import com.example.espeedboatadmin.client.Endpoint;
import com.example.espeedboatadmin.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ReviewService {
    @GET(Endpoint.REVIEW_LIST)
    Call<Response> getReviews(@Header("Authorization") String token);
}
