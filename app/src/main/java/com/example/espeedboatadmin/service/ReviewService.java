package com.example.espeedboatadmin.service;

import com.example.espeedboatadmin.client.Endpoint;
import com.example.espeedboatadmin.model.ResponseReview;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ReviewService {
    @GET(Endpoint.REVIEW_LIST)
    Call<ResponseReview> getReviews(@Header("Authorization") String token);
}
