package com.example.dnjsr.smtalk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dnjsr.smtalk.Tool.Tool;
import com.example.dnjsr.smtalk.globalVariables.AllRoomUser;
import com.example.dnjsr.smtalk.globalVariables.CurrentUserInfo;
import com.example.dnjsr.smtalk.globalVariables.MySocketManager;
import com.example.dnjsr.smtalk.globalVariables.SelectedRoomInfo;
import com.example.dnjsr.smtalk.info.ChatObjWithOnlyId;
import com.example.dnjsr.smtalk.info.ChatObject;
import com.example.dnjsr.smtalk.info.UserInfo;
import com.example.dnjsr.smtalk.userInfoUpdate.GetAllChats;
import com.example.dnjsr.smtalk.userInfoUpdate.NewChatSend;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class ChatRoomActivity extends AppCompatActivity {

    Socket socket;
    Handler handler;

    Emitter.Listener emitterInit=  new Emitter.Listener() {
        Gson gson = new Gson();
        ChatObjWithOnlyId newChatObj;

        @Override
        public void call(Object... args) {
            try {
                JSONObject obj = (JSONObject)args[0];
                newChatObj = (ChatObjWithOnlyId) gson.fromJson(String.valueOf(obj),ChatObjWithOnlyId.class);
                ChatObject chatObject = new ChatObject(newChatObj.getCreateAt(),newChatObj.getChat(),AllRoomUser.getAllRoomUsers().get(newChatObj.getUser()),SelectedRoomInfo.getSelectedRoomInfo(),1);
                chatObjectList.add(chatObject);
                handler.sendEmptyMessage(0);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    };
    Emitter.Listener emmiterNewChat = new Emitter.Listener() {


        @Override
        public void call(Object... args){
            try {
                JSONObject obj = new JSONObject();
                obj.put("roomId", SelectedRoomInfo.getSelectedRoomInfo().get_id());
                //HashMap object = new HashMap();
                socket.emit("init",obj);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    };


    static List<ChatObject> chatObjectList =new ArrayList<ChatObject>();

    public static ChatRoomRecyclerViewAdapter chatRoomRecyclerViewAdapter= new ChatRoomRecyclerViewAdapter(chatObjectList);

    public static void setchatsList(List<ChatObject> list) {
        chatObjectList = list;
        chatRoomRecyclerViewAdapter.setItems(chatObjectList);
    }
    public static void clearchatList(){
        chatObjectList.clear();
        chatRoomRecyclerViewAdapter.setItems(chatObjectList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        GetAllChats getAllChats = new GetAllChats();
        getAllChats.getAllChats(SelectedRoomInfo.getSelectedRoomInfo().get_id());
        final RecyclerView chatRoomRecyclerView = findViewById(R.id.chatroomactivity_recyclerview);
        chatRoomRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRoomRecyclerView.setAdapter(chatRoomRecyclerViewAdapter);
        Log.d("12321","CA resume");



    }
    public void onDestroy() {
        super.onDestroy();
        socket.disconnect();
        socket.off(Socket.EVENT_CONNECT,emitterInit);
        socket.off(Socket.EVENT_CONNECT,emmiterNewChat);
        socket = null;
        Log.d("12321","소켓 연결 해제");
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        Tool tool = new Tool();


        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0) {
                    setchatsList(chatObjectList);
                }
            }
        };




        try  {
            socket = MySocketManager.getManager().socket("/chat");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        socket.connect();

        socket.on(Socket.EVENT_CONNECT,emmiterNewChat ).on("newChat",emitterInit);




        FloatingActionButton btChat = findViewById(R.id.chatroomactivity_floatingbutton_send);
        FloatingActionButton btPhoto = findViewById(R.id.chatroomactivity_floatingbutton_photo);
        final EditText edtChat = findViewById(R.id.chatroomactivity_edittext_message);





        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#2f2f30"));
        }

        Log.d("12321", SelectedRoomInfo.getSelectedRoomInfo().get_id());

        ActionBar actionBar = getSupportActionBar();

        String title = "그룹톡";
        if(SelectedRoomInfo.getSelectedRoomInfo().getUsersList().size()<3)
            title = AllRoomUser.getAllRoomUsers().get(tool.getOther_Id(SelectedRoomInfo.getSelectedRoomInfo())).getUserName();
        actionBar.setTitle(title);
        btChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*ChatObject chatObject = new ChatObject(null,CurrentUserInfo.getUser().getUserInfo().get_id(),edtChat.getText().toString(),"",0);
                chatObjectList.add(chatObject);
                chatRoomRecyclerViewAdapter.setItems(chatObjectList);*/
                NewChatSend newChatSend = new NewChatSend();
                newChatSend.newChat(CurrentUserInfo.getUser().getUserInfo().get_id(),SelectedRoomInfo.getSelectedRoomInfo().get_id(),edtChat.getText().toString());

                edtChat.setText("");
            }
        });
        btPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*ChatObject chatObject = new ChatObject(null,SelectedUserInfo.getUser().getUserInfo().get_id(),edtChat.getText().toString(),"",0);
                chatObjectList.add(chatObject);
                chatRoomRecyclerViewAdapter.setItems(chatObjectList);*/
                edtChat.setText("");
            }
        });

    }

    static class ChatRoomRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        List<ChatObject> items;
        boolean isMe;


        public ChatRoomRecyclerViewAdapter(List<ChatObject> chatList) {
            items = chatList;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view;

            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_my_chat_bubble, viewGroup, false);

            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

                HashMap asa = AllRoomUser.getAllRoomUsers();
                String sad = items.get(i).getUser().get_id();
                Bitmap img = AllRoomUser.getAllRoomUsers().get(items.get(i).getUser().get_id()).getImage();
                //((CustomViewHolder) viewHolder).chatBubble_iv_profile.setImageBitmap(AllRoomUser.getAllRoomUsers().get(items.get(i).getUser().get_id()).getImage());
                ((CustomViewHolder) viewHolder).chatBubble_iv_profile.setImageBitmap(img);
                ((CustomViewHolder) viewHolder).chatBubble_tv_chat.setText(items.get(i).getChat());
                ((CustomViewHolder) viewHolder).getChatBubble_tv_userName.setText(AllRoomUser.getAllRoomUsers().get(items.get(i).getUser().get_id()).getUserName());

        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public void setItems(List<ChatObject> chatList) {
            items = chatList;
            notifyDataSetChanged();
        }


        private class CustomViewHolder extends RecyclerView.ViewHolder {
            private ImageView chatBubble_iv_profile;
            private TextView chatBubble_tv_chat;
            private TextView getChatBubble_tv_userName;

            public CustomViewHolder(View view) {
                super(view);
                    chatBubble_iv_profile = view.findViewById(R.id.my_chatBubble_iv_profileImage);
                    chatBubble_tv_chat = view.findViewById(R.id.my_chatBubble_tv_chat);
                    getChatBubble_tv_userName = view.findViewById(R.id.my_chatBubble_tv_userName);
                /*} else {
                    chatBubble_iv_profile = view.findViewById(R.id.othres_chatBubble_iv_profileImage);
                    chatBubble_tv_chat = view.findViewById(R.id.othres_chatBubble_tv_chat);
                    getChatBubble_tv_userName = view.findViewById(R.id.othres_chatBubble_tv_userName);
                }*/


            }
        }
    }

}
