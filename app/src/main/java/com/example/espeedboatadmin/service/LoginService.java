package com.example.espeedboatadmin.service;

import com.example.espeedboatadmin.client.Endpoint;
import com.example.espeedboatadmin.model.ResponseAuth;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Header;

public interface LoginService {
    @FormUrlEncoded
    @POST(Endpoint.LOGIN_ADMIN)
    Call<ResponseAuth> postLogin(@Field("email") String email, @Field("password") String password);
}
