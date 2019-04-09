package com.example.dnjsr.smtalk.userInfoUpdate;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.example.dnjsr.smtalk.api.RetrofitApi;
import com.example.dnjsr.smtalk.globalVariables.CurrentUserInfo;
import com.example.dnjsr.smtalk.globalVariables.ServerURL;
import com.example.dnjsr.smtalk.result.CreateRoomResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoomCreate {
    String url = ServerURL.getUrl();
    public void createRoom(final String myid, final String friendId , final Intent intent, final Activity activity){

        final String myId1 = myid;
        final String friendId1 = friendId;

        try {
            HashMap<String, String> input = new HashMap<>();
            input.put("roomId", "");
            input.put("userId", myId1);
            input.put("friendId",friendId1);
            Log.d("12321",myId1+" " +friendId1);


            Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create()).build();
            RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
            retrofitApi.createRoom(input).enqueue(new Callback<CreateRoomResult>() {
                @Override
                public void onResponse(Call<CreateRoomResult> call, Response<CreateRoomResult> response) {
                    if (response.isSuccessful()) {
                        CreateRoomResult map = response.body();
                        if (map != null) {
                            switch (map.getResult()) {
                                case 0:
                                    Log.d("12321", "fail making room");
                                    break;
                                case 1:
                                    Log.d("12321", "room create ok");
                                    CurrentUserInfo.getUser().getUserInfo().getRoomsList().add(map.getNewRoom());
                                    Log.d("12321",map.getNewRoom().getUsersList().get(0).get("_id"));
                                    activity.startActivity(intent);
                                    activity.finish();
                                    break;
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<CreateRoomResult> call, Throwable t) {
                    Log.d("12321","fail asd dasdsaads");
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
