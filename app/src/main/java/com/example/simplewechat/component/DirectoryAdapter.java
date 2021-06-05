package com.example.simplewechat.component;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.simplewechat.ChatActivity;
import com.example.simplewechat.R;
import com.example.simplewechat.domain.Friends;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


//和FriendAdapter不同，FA是ListView Adapter，而DA是RecycleView Adapter且DA有大写字母和隐藏线
public class DirectoryAdapter extends RecyclerView.Adapter<DirectoryAdapter.ViewHolder> {
    List<Friends> friendsList=new ArrayList<>();
    Context context;
    LayoutInflater inflater;

    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout letterlayout;
        TextView letter;
        ImageView img;
        TextView name;
        View view;
        View underline;

        public ViewHolder(View itemview){
            super(itemview);
            view=itemview;
            letterlayout=itemview.findViewById(R.id.letterlayout);
            letter=itemview.findViewById(R.id.letter);
            img=itemview.findViewById(R.id.img);
            name=itemview.findViewById(R.id.name);
            underline=itemview.findViewById(R.id.underline);
        }
    }

    public DirectoryAdapter(List<Friends> friendsList,Context context){
        this.friendsList=friendsList;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.directory_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Friends friends=friendsList.get(position);
        holder.img.setImageResource(friends.getFriendId());//图片用id存储
        holder.name.setText(friends.getName());
        //点击进入对话页面
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ChatActivity.class);
                intent.putExtra("name",friends.getName());
                context.startActivity(intent);
            }
        });

        //根据position获取首字母作为目录mark
        String mark=friendsList.get(position).getStart();
        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if(position==getPositionForSection(mark)){
            holder.letterlayout.setVisibility(View.VISIBLE);
            holder.letter.setText(mark);
        }else{
            holder.letterlayout.setVisibility(View.GONE);
        }

    }

    //该分类首字母的位置
    private int getPositionForSection(String mark){
        for(int i=0;i<getItemCount();i++){
            String sortStr=friendsList.get(i).getStart();
            if(mark.equalsIgnoreCase(sortStr)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }
}
