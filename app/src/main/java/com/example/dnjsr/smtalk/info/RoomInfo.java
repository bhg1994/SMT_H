package com.example.dnjsr.smtalk.info;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class RoomInfo implements Parcelable {
    String lastChat;
    int unreadCount;
    ArrayList<UserInfo> usersList;
    Date createAt;
    String _id;

    public String getLastChat() {
        return lastChat;
    }

    public void setLastChat(String lastChat) {
        this.lastChat = lastChat;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }

    public ArrayList<UserInfo> getUsersList() {
        return usersList;
    }

    public void setUsersList(ArrayList<UserInfo> usersList) {
        this.usersList = usersList;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public static Creator<RoomInfo> getCREATOR() {
        return CREATOR;
    }

    protected RoomInfo(Parcel in) {
        lastChat = in.readString();
        unreadCount = in.readInt();
        usersList = in.createTypedArrayList(UserInfo.CREATOR);
        _id = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lastChat);
        dest.writeInt(unreadCount);
        dest.writeTypedList(usersList);
        dest.writeString(_id);
    }
}

