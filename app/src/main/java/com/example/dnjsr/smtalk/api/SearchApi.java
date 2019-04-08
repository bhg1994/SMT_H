package com.example.dnjsr.smtalk.api;


import com.example.dnjsr.smtalk.result.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SearchApi {

    @GET("/search/user/{userId}")
    Call<SearchResult> getUserId(@Path("userId") String userId);
}