package com.example.dnjsr.smtalk.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dnjsr.smtalk.ChatRoomActivity;
import com.example.dnjsr.smtalk.R;
import com.example.dnjsr.smtalk.info.RoomInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ChatFragment extends android.support.v4.app.Fragment {
    private ChatFragmentRecyclerViewAdapter chatFragmentRecyclerViewAdapter;
    private RecyclerView fragment_chat_recyclerview;

    List<RoomInfo> roomAdapterList = new ArrayList<>();

    public List<RoomInfo> getRoomAdapterList() {
        return roomAdapterList;
    }

    public void setRoomAdapterList(List<RoomInfo> roomAdapterList) {
        this.roomAdapterList = roomAdapterList;
    }

    /*  Bundle recive_bundle = getArguments();
        RoomInfo roominfo = recive_bundle.getParcelable("roominfo");
    */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat,container,false);
        fragment_chat_recyclerview = view.findViewById(R.id.chatfragment_recyclerview);
        fragment_chat_recyclerview.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
        chatFragmentRecyclerViewAdapter = new ChatFragmentRecyclerViewAdapter();
        fragment_chat_recyclerview.setAdapter(chatFragmentRecyclerViewAdapter);

        return view;
    }


    class ChatFragmentRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


        public ChatFragmentRecyclerViewAdapter() {

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chatroom,viewGroup,false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
            ((CustomViewHolder)viewHolder).chatroomitem_imageview.setImageResource(R.drawable.icon_account);
            ((CustomViewHolder)viewHolder).chatroomitem_textview_chatroomname.setText(roomAdapterList.get(i).getUsersList().toString());

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),ChatRoomActivity.class);
                    intent.putExtra("roominfo",roomAdapterList.get(i));
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return roomAdapterList.size();
        }

        private class CustomViewHolder extends RecyclerView.ViewHolder {
            private ImageView chatroomitem_imageview;
            private TextView chatroomitem_textview_chatroomname;
            private TextView chatroomitem_textviewoomnumber;
            private TextView chatroomitem_textview_chatroomlastmessage;
            private TextView chatroomitem_textview_chatroomlastmessagetime;
            public CustomViewHolder(View view) {
                super(view);
                chatroomitem_imageview = view.findViewById(R.id.chatroomitem_imageview);
                chatroomitem_textview_chatroomname = view.findViewById(R.id.chatroomitem_textview_chatroomname);

                chatroomitem_textview_chatroomlastmessage = view.findViewById(R.id.chatroomitem_textview_chatroomlastmessage);
                chatroomitem_textview_chatroomlastmessagetime = view.findViewById(R.id.chatroomitem_textview_chatroomlastmessagetime);
            }
        }
    }
}
