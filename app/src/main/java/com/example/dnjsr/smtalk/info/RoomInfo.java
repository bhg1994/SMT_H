package com.example.dnjsr.smtalk.info;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class RoomInfo implements Parcelable {
    int unreadCount;
    ArrayList<HashMap<String,String>> usersList;
    String _id;

    public RoomInfo(int unreadCount, ArrayList<HashMap<String, String>> usersList, String _id) {
        this.unreadCount = unreadCount;
        this.usersList = usersList;
        this._id = _id;
    }


    protected RoomInfo(Parcel in) {
        unreadCount = in.readInt();
        _id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(unreadCount);
        dest.writeString(_id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RoomInfo> CREATOR = new Creator<RoomInfo>() {
        @Override
        public RoomInfo createFromParcel(Parcel in) {
            return new RoomInfo(in);
        }

        @Override
        public RoomInfo[] newArray(int size) {
            return new RoomInfo[size];
        }
    };

    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }

    public ArrayList<HashMap<String, String>> getUsersList() {
        return usersList;
    }

    public void setUsersList(ArrayList<HashMap<String, String>> usersList) {
        this.usersList = usersList;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}

