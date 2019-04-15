package com.example.dnjsr.smtalk.result;

import com.example.dnjsr.smtalk.info.RoomInfo;
import com.example.dnjsr.smtalk.info.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class RoomListCallResult {

    int result;
    ArrayList<RoomInfo> roomsList;
    List<List<UserInfo>> usersLists;

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

    public List<List<UserInfo>> getFriendsList() {
        return usersLists;
    }

    public void setFriendsList(List<List<UserInfo>> friendsList) {
        this.usersLists = friendsList;
    }


}
