package com.example.dnjsr.smtalk.api;

import com.example.dnjsr.smtalk.result.JoinResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HTTP;


public interface FriendDeleteApi {
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "/friend", hasBody = true)
    Call<JoinResult> deleteFriend(@FieldMap HashMap<String,String> map);
}
