package com.example.dnjsr.smtalk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dnjsr.smtalk.api.FriendAddApi;
import com.example.dnjsr.smtalk.api.FriendDeleteApi;
import com.example.dnjsr.smtalk.api.RetrofitApi;
import com.example.dnjsr.smtalk.fragment.PeopleFragment;
import com.example.dnjsr.smtalk.globalVariables.CurrentUserInfo;
import com.example.dnjsr.smtalk.globalVariables.SelectedUserInfo;
import com.example.dnjsr.smtalk.globalVariables.ServerURL;
import com.example.dnjsr.smtalk.info.User;
import com.example.dnjsr.smtalk.info.UserInfo;
import com.example.dnjsr.smtalk.result.JoinResult;
import com.example.dnjsr.smtalk.userInfoUpdate.UserInfoUpdate;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {
    private ImageView profileactivity_imageview_profileimage;
    private TextView profileactivity_textview_username;
    private TextView profileactivity_textview_usermessage;
    private Button profileactivity_button_friendAdd;
    String serverUrl = new ServerURL().getUrl();
    UserInfo currentUser = CurrentUserInfo.getUser().getUserInfo();
    UserInfoUpdate userInfoUpdate = new UserInfoUpdate();
    boolean isFriend = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#2f2f30"));
        }

        final UserInfo userinfo = SelectedUserInfo.getUser().getUserInfo();

        profileactivity_imageview_profileimage = findViewById(R.id.profileactivity_imageview_profileimage);
        profileactivity_textview_username = findViewById(R.id.profileactivity_textview_username);
        profileactivity_textview_usermessage =findViewById(R.id.profileactivity_textview_usermessage);
        profileactivity_button_friendAdd = findViewById(R.id.profileactivity_button_friendAdd);

        profileactivity_imageview_profileimage.setImageBitmap(userinfo.getImage());
        profileactivity_textview_username.setText(userinfo.getUserName());
        profileactivity_textview_usermessage.setText(userinfo.getComment());

        for (int i=0; i<currentUser.getFriendsList().size();i++){
            if(currentUser.getFriendsList().get(i).get_id().equals(userinfo.get_id())){
                isFriend=true;
                break;
            }
        }

        profileactivity_imageview_profileimage.setOnClickListener(new View.OnClickListener() {               //image눌러 화면이동
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ProfilepictureActivity.class);
                startActivity(intent);
            }
        });

        if(isFriend)
            profileactivity_button_friendAdd.setText("친구 삭제");
        else
            profileactivity_button_friendAdd.setText("친구 추가");

        profileactivity_button_friendAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myId = CurrentUserInfo.getUser().getUserInfo().get_id();
                String friendId = userinfo.get_id();
                if(isFriend)
                    DeleteFriend(myId,friendId);
                else
                    AddFriend(myId,friendId);
                userInfoUpdate.Update(CurrentUserInfo.getUser().getUserInfo().get_id(),ProfileActivity.this);
            }
        });
    }

    public void AddFriend(String userId, String friendId){
        HashMap<String, String> input = new HashMap<>();
        input.put("_id", userId);
        input.put("friendId",friendId);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(serverUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();

        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        retrofitApi.addFriend(input).enqueue(new Callback<JoinResult>() {
            @Override
            public void onResponse(Call<JoinResult> call, Response<JoinResult> response) {
                if (response.isSuccessful()) {
                    JoinResult searchResult = response.body();
                    switch (searchResult.getResult()) {
                        case 0:
                            Log.d("test123","FAfail");
                            break;
                        case 1:
                            Log.d("test123","FAsucess");
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<JoinResult> call, Throwable t) {

            }
        });
    }
    public void DeleteFriend(String userId, String friendId){
        HashMap<String, String> input = new HashMap<>();
        input.put("_id", userId);
        input.put("friendId",friendId);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(serverUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();

        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        retrofitApi.deleteFriend(input).enqueue(new Callback<JoinResult>() {
            @Override
            public void onResponse(Call<JoinResult> call, Response<JoinResult> response) {
                if (response.isSuccessful()) {
                    JoinResult result = response.body();
                    switch (result.getResult()) {
                        case 0:
                            Log.d("test123","FDfail");
                            break;
                        case 1:
                            Log.d("test123","FDsucess");
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<JoinResult> call, Throwable t) {

            }
        });
    }
}
