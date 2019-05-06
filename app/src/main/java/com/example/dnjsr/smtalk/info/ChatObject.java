package com.example.dnjsr.smtalk.info;

import java.util.Date;

public class ChatObject {
    Date createAt;
    String userId;
    String chat;
    String roomId;
    UserInfo user;
    RoomInfo room;

    public RoomInfo getRoom() {
        return room;
    }

    public void setRoom(RoomInfo room) {
        this.room = room;
    }

    int unreadCount;

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public ChatObject(Date createAt, String chat, UserInfo user, RoomInfo room, int unreadCount) {
        this.createAt = createAt;
        this.chat = chat;
        this.user = user;
        this.room = room;
        this.unreadCount = unreadCount;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String user) {
        this.userId = user;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }
}
