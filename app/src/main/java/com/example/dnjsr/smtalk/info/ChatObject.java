package com.example.dnjsr.smtalk.info;

import java.util.Date;

public class ChatObject {
    Date createAt;
    String user;
    String chat;
    String room;
    int unreadCount;

    public ChatObject(Date createAt, String user, String chat, String room, int unreadCount) {
        this.createAt = createAt;
        this.user = user;
        this.chat = chat;
        this.room = room;
        this.unreadCount = unreadCount;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }
}
