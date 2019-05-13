package com.example.dnjsr.smtalk;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dnjsr.smtalk.info.UserInfo;

import java.util.List;

public class GroupRoomCreateActivity extends AppCompatActivity {
    List<UserInfo> userInfos;
    Boolean[] isChecked;
    SelectUserRecyclerViewAdapter1 selectUserRecyclerViewAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_room_create);

        userInfos = ((MainActivity)MainActivity.mContext).getUserInfos();
        recyclerView = findViewById(R.id.Rv_UserSelect);
        selectUserRecyclerViewAdapter = new SelectUserRecyclerViewAdapter1(userInfos);
        recyclerView.setAdapter(selectUserRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button bt_Ok = findViewById(R.id.bt_Ok);
        bt_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectUserRecyclerViewAdapter.notifyDataSetChanged();
                Log.d("12321", Integer.toString(selectUserRecyclerViewAdapter.items.size()));
                Log.d("12321", Integer.toString(userInfos.size()));
                selectUserRecyclerViewAdapter.setItems(userInfos);
            }
        });
    }
    class SelectUserRecyclerViewAdapter extends RecyclerView.Adapter<SelectUserRecyclerViewAdapter.CustomViewHolder>{
        Boolean[] isChecked = new Boolean[userInfos.size()];
        private List<UserInfo> items;
        public SelectUserRecyclerViewAdapter(List<UserInfo> list)
        {
            this.items=list;
            Log.d("12321", Integer.toString(items.size())+"tlwkr");
        }
        @NonNull
        @Override
        public SelectUserRecyclerViewAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_check,parent,false);
            Log.d("12321", Integer.toString(items.size())+"온크리에이트");
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SelectUserRecyclerViewAdapter.CustomViewHolder holder, final int position) {
            Log.d("12321", Integer.toString(items.size())+"바인드뷰홀더");
            ((CustomViewHolder)holder).profileImage.setImageBitmap(items.get(position).getImage());
            ((CustomViewHolder)holder).textview_name.setText(items.get(position).getUserName());
            ((CustomViewHolder)holder).checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox checkBox = (CheckBox)v;
                    if(checkBox.isChecked())
                        checkBox.setChecked(false);
                    else
                        checkBox.setChecked(true);
                    isChecked[position] = checkBox.isChecked();
                }
            });
        }

        @Override
        public int getItemCount() {
            return 0;
        }
        public void setItems(List<UserInfo> list){
            items = list;
            notifyDataSetChanged();
        }
        private class CustomViewHolder extends RecyclerView.ViewHolder {
            public ImageView profileImage;
            public TextView textview_name;
            public CheckBox checkBox;
            public CustomViewHolder(View view) {
                super(view);
                profileImage = view.findViewById(R.id.Iv_profile_forCheck);
                textview_name = view.findViewById(R.id.Tv_userName_forCheck);
                checkBox = view.findViewById(R.id.checkBox);
            }
        }
    }



    class SelectUserRecyclerViewAdapter1 extends RecyclerView.Adapter<SelectUserRecyclerViewAdapter1.CustomViewHolder>{
        Boolean[] isChecked = new Boolean[userInfos.size()];
        private List<UserInfo> items;

        public SelectUserRecyclerViewAdapter1(List<UserInfo> list)
        {
            this.items=list;
            Log.d("12321", Integer.toString(items.size())+"tlwkr");
        }

        @Override
        public CustomViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_check,parent,false);
            Log.d("12321", Integer.toString(items.size())+"온크리에이트");
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
            Log.d("12321", Integer.toString(items.size())+"바인드뷰홀더");
            ((CustomViewHolder)holder).profileImage.setImageBitmap(items.get(position).getImage());
            ((CustomViewHolder)holder).textview_name.setText(items.get(position).getUserName());
            ((CustomViewHolder)holder).checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox checkBox = (CheckBox)v;
                    if(checkBox.isChecked())
                        checkBox.setChecked(false);
                    else
                        checkBox.setChecked(true);
                    isChecked[position] = checkBox.isChecked();
                }
            });

        }

        @Override
        public int getItemCount() {
            return 0;
        }
        public void setItems(List<UserInfo> list){
            items = list;
            notifyDataSetChanged();
        }
        public void getIsChecked(Boolean[] isChecked){
            isChecked = this.isChecked;
        }

        private class CustomViewHolder extends RecyclerView.ViewHolder {
            public ImageView profileImage;
            public TextView textview_name;
            public CheckBox checkBox;
            public CustomViewHolder(View view) {
                super(view);
                profileImage = view.findViewById(R.id.Iv_profile_forCheck);
                textview_name = view.findViewById(R.id.Tv_userName_forCheck);
                checkBox = view.findViewById(R.id.checkBox);
            }
        }
    }
}
