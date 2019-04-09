package com.example.dnjsr.smtalk.result;

import com.example.dnjsr.smtalk.info.RoomInfo;

public class CreateRoomResult {
    int result;
    RoomInfo newRoom;

    public CreateRoomResult(int result, RoomInfo newRoom) {
        this.result = result;
        this.newRoom = newRoom;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public RoomInfo getNewRoom() {
        return newRoom;
    }

    public void setNewRoom(RoomInfo newRoom) {
        this.newRoom = newRoom;
    }
}
