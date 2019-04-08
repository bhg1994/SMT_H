package com.example.dnjsr.smtalk.userInfoUpdate;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.dnjsr.smtalk.api.RetrofitApi;
import com.example.dnjsr.smtalk.globalVariables.CurrentUserInfo;
import com.example.dnjsr.smtalk.globalVariables.ServerURL;
import com.example.dnjsr.smtalk.info.UserInfo;
import com.example.dnjsr.smtalk.result.LoginResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserInfoUpdate {
    String url = ServerURL.getUrl();
    int resultCode;
    public int Update(String _id){
        try {
            HashMap<String, String> input = new HashMap<>();
            input.put("_Id", _id);



            Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create()).build();
            RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
            retrofitApi.post_idForUpdate(input).enqueue(new Callback<LoginResult>() {
                @Override
                public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                    if (response.isSuccessful()) {
                        LoginResult map = response.body();
                        if (map != null) {
                            switch (map.getResult()) {
                                case 0:
                                    Log.d("12321","update fail");
                                    resultCode = 0;
                                    break;
                                case 1:
                                    Log.d("12321","update ok");
                                    UserInfo userinfo = map.getUserInfo();
                                    userinfo.setChange(true);
                                    CurrentUserInfo.getUser().setUserInfo(userinfo);
                                    resultCode = 1;
                                    break;
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResult> call, Throwable t) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultCode;

    }
}