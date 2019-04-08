package com.example.dnjsr.smtalk.result;

import com.example.dnjsr.smtalk.info.RoomInfo;
import com.example.dnjsr.smtalk.info.UserInfo;

import java.util.ArrayList;

public class RoomListCallResult {

    int result;
    ArrayList<RoomInfo> roomsList;
    ArrayList<UserInfo> friendsList;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public ArrayList<RoomInfo> getRoomsList() {
        return roomsList;
    }

    public void setRoomsList(ArrayList<RoomInfo> roomsList) {
        this.roomsList = roomsList;
    }

    public ArrayList<UserInfo> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(ArrayList<UserInfo> friendsList) {
        this.friendsList = friendsList;
    }
}
