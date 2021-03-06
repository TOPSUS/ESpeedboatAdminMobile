package com.example.espeedboatadmin.service;

import com.example.espeedboatadmin.client.Endpoint;
import com.example.espeedboatadmin.model.Response;
import com.example.espeedboatadmin.model.ResponseLogout;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LogoutService {
    @FormUrlEncoded
    @POST(Endpoint.LOGOUT)
    Call<Response> postLogout(@Field("token") String token);
}
