package com.example.dnjsr.smtalk.api;

import com.example.dnjsr.smtalk.result.JoinResult;

import java.util.HashMap;

import retrofit2.Call;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FriendAddApi {
    @FormUrlEncoded
    @POST("/friend")
    Call<JoinResult> postUserInfo(@FieldMap HashMap<String,String> map);
}
