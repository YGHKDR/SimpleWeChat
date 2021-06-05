package com.example.simplewechat.component;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.simplewechat.ChatActivity;
import com.example.simplewechat.R;
import com.example.simplewechat.domain.Friends;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

//好友列表的List的Adapter

public class FriendAdapter extends ArrayAdapter<Friends> {
    private int resourceId;
    private List<Friends> datas;
    private Context mContext;

    public FriendAdapter(Context context, int textViewResourceId, List<Friends> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
        datas=objects;
        mContext=context;  //构造函数初始化
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Friends friends=getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
           view = LayoutInflater.from(getContext()).inflate(R.layout.friend_item,null);
           viewHolder=new ViewHolder();
           viewHolder.friendImage=(ImageView)view.findViewById(R.id.friend_image);
           viewHolder.friendName=(TextView)view.findViewById(R.id.friend_text);
           viewHolder.menuView=(TextView)view.findViewById(R.id.menu);
           view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }

        view.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Friends friend=datas.get(position);
                String name=friend.getName();
                //进入对话框
                Intent intent= new Intent();
                intent.putExtra("name",name);
                intent.setClass(mContext, ChatActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        viewHolder.friendImage.setImageResource(friends.getFriendId());
        viewHolder.friendName.setText(friends.getName());

        //删除按钮
        viewHolder.menuView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                datas.remove(friends);
                notifyDataSetChanged();
            }
        });

        MessageSlideLayout slideLayout=(MessageSlideLayout)view;
        slideLayout.setOnStateChangeListener(new MyOnStateChangeListener());

        return view;
    }

    public MessageSlideLayout slideLayout=null;
    private class MyOnStateChangeListener implements MessageSlideLayout.OnStateChangeListener {
        @Override
        public void onClose(MessageSlideLayout layout) { // 菜单关闭后，选中item为空
            if (slideLayout == layout){
                slideLayout = null;
            }
        }

        @Override
        public void onDown(MessageSlideLayout layout) {
            if (slideLayout != null && slideLayout != layout) { // 当打开其他item的菜单时，关闭已打开的菜单
                slideLayout.closeMenu();
            }
        }

        @Override
        public void onOpen(MessageSlideLayout layout) {
            if (slideLayout == null){
                slideLayout = layout;
            }
        }

    }

    class ViewHolder{
        ImageView friendImage;
        TextView friendName;
        TextView menuView;
    }
}
