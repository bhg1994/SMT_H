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
import com.example.dnjsr.smtalk.Tool.Tool;
import com.example.dnjsr.smtalk.globalVariables.CurrentUserInfo;
import com.example.dnjsr.smtalk.globalVariables.FriendsInfo;
import com.example.dnjsr.smtalk.globalVariables.SelectedRoomInfo;
import com.example.dnjsr.smtalk.globalVariables.SelectedUserInfo;
import com.example.dnjsr.smtalk.info.RoomInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

public class ChatFragment extends android.support.v4.app.Fragment {

    private RecyclerView fragment_chat_recyclerview;

    List<RoomInfo> roomAdapterList =new ArrayList<>();

    public ChatFragmentRecyclerViewAdapter chatFragmentRecyclerViewAdapter= new ChatFragmentRecyclerViewAdapter(roomAdapterList);

    public void setRoomAdapterList(List<RoomInfo> roomsList) {
        Log.d("12321",Integer.toString(roomsList.size())+" before set");
        chatFragmentRecyclerViewAdapter.setItems(roomsList);
        Log.d("12321",Integer.toString(roomsList.size())+" after set");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat,container,false);

        fragment_chat_recyclerview = view.findViewById(R.id.chatfragment_recyclerview);
        fragment_chat_recyclerview.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
        fragment_chat_recyclerview.setAdapter(chatFragmentRecyclerViewAdapter);


        return view;
    }


    class ChatFragmentRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        List<RoomInfo> items ;


        public ChatFragmentRecyclerViewAdapter(List<RoomInfo> roomInfoList) {
            items = roomInfoList;
            Log.d("12321",Integer.toString(items.size())+" chatFRVA");
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chatroom,viewGroup,false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
            Tool tool = new Tool();

            Log.d("12321", Integer.toString(items.size())+" view holder1");
            if(items.size()>0) {
                Log.d("12321", Integer.toString(items.size())+" view holder2");
                //String _id = "5cb4518d7c08cb04e1aeb60c";
                //String _id2 = items.get(i).getUsersList().get(1).get_id();
                String _id = tool.getOther_Id(items.get(i));

                ((CustomViewHolder)viewHolder).chatroomitem_imageview.setImageBitmap(FriendsInfo.getFriendsInfo().get(_id).getImage());
                ((CustomViewHolder)viewHolder).chatroomitem_textview_chatroomname.setText(FriendsInfo.getFriendsInfo().get(_id).getUserName());
                ((CustomViewHolder)viewHolder).chatroomitem_textview_chatroomlastmessagetime.setText("마지막 메세지 시간 구현 예정");
                ((CustomViewHolder)viewHolder).chatroomitem_textview_chatroomlastmessage.setText("마지막 메세지 구현 예정");
                //((CustomViewHolder)viewHolder).chatroomitem_textview_chatroomlastmessage.setText(items.get(i).getLastChat());

                if(items.get(i).getUnreadCount()!=0) {
                    ((CustomViewHolder) viewHolder).chatroomitem_textview_unreadcount.setVisibility(View.VISIBLE);
                    ((CustomViewHolder) viewHolder).chatroomitem_textview_unreadcount.setText(Integer.toString(items.get(i).getUnreadCount()));
                }
            }
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String _id = "5cb4518d7c08cb04e1aeb60c";
                        Log.d("12321","onclick");
                        SelectedRoomInfo.setSelectedRoomInfo(items.get(i));
                        SelectedUserInfo.getUser().setUserInfo(FriendsInfo.getFriendsInfo().get(_id));
                        Intent intent = new Intent(v.getContext(), ChatRoomActivity.class);
                        startActivity(intent);
                    }
                });

        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public void setItems(List<RoomInfo> roomInfoList){
            items = roomInfoList;
            Log.d("12321",Integer.toString(items.size())+" setitems");
            notifyDataSetChanged();
        }



        private class CustomViewHolder extends RecyclerView.ViewHolder {
            private ImageView chatroomitem_imageview;
            private TextView chatroomitem_textview_chatroomname;
            private TextView chatroomitem_textview_chatroomlastmessage;
            private TextView chatroomitem_textview_chatroomlastmessagetime;
            private TextView chatroomitem_textview_unreadcount;

            public CustomViewHolder(View view) {
                super(view);
                chatroomitem_imageview = view.findViewById(R.id.chatroomitem_imageview);
                chatroomitem_textview_chatroomname = view.findViewById(R.id.chatroomitem_textview_chatroomname);
                chatroomitem_textview_chatroomlastmessage = view.findViewById(R.id.chatroomitem_textview_chatroomlastmessage);
                chatroomitem_textview_chatroomlastmessagetime = view.findViewById(R.id.chatroomitem_textview_chatroomlastmessagetime);
                chatroomitem_textview_unreadcount = view.findViewById(R.id.chatroomitem_textview_unreadcount);
            }
        }
    }
}
