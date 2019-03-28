package com.example.dnjsr.smtalk.api;

import com.example.dnjsr.smtalk.result.LoginResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginApi {
    @FormUrlEncoded
    @POST("/login")
    Call<LoginResult> postUserInfo(@FieldMap HashMap<String,String> map);
}
