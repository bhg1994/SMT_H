package com.example.dnjsr.smtalk;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.dnjsr.smtalk.Tool.Tool;
import com.example.dnjsr.smtalk.globalVariables.CurrentUserInfo;
import com.example.dnjsr.smtalk.globalVariables.FriendsInfo;
import com.example.dnjsr.smtalk.globalVariables.SelectedRoomInfo;
import com.example.dnjsr.smtalk.globalVariables.SelectedUserInfo;
import com.example.dnjsr.smtalk.info.RoomInfo;

public class ChatRoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        Tool tool = new Tool();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#2f2f30"));
        }

        Log.d("12321", SelectedRoomInfo.getSelectedRoomInfo().get_id());

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(FriendsInfo.getFriendsInfo().get(tool.getOther_Id(SelectedRoomInfo.getSelectedRoomInfo())).getUserName());
        //actionBar.setTitle(FriendsInfo.getFriendsInfo().get(SelectedRoomInfo.getSelectedRoomInfo().getUsersList().get(1)).getUserName());
    }
}
