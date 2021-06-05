package com.example.simplewechat.component;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.simplewechat.R;
import com.example.simplewechat.domain.Friends;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
    private Context mContext;
    private List<Friends> mFriendList;
    private List<Integer> mFriendImg;
    private List<String> mName;
    private List<String> mFriendContent;
    private List<List<Integer>> mItemPic;
    private List<Integer> itempic;
    private List<Integer> mTimes;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView image1;
        ImageView image2;
        ImageView image3;
        ImageView imageStar;
        ImageView friendImg;
        TextView name;
        TextView time;
        TextView friendContent;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            image1 = (ImageView) view.findViewById(R.id.friend_img1);
            image2 = (ImageView) view.findViewById(R.id.friend_img2);
            image3 = (ImageView) view.findViewById(R.id.friend_img3);
            imageStar=(ImageView) view.findViewById(R.id.star1);
            friendImg=(ImageView) view.findViewById(R.id.news_img);
            name=(TextView) view.findViewById(R.id.friend_name);
            time=(TextView) view.findViewById(R.id.time);
            friendContent=(TextView) view.findViewById(R.id.writeContent);
        }
    }

    public NewsAdapter(List<Friends> friendList,List<Integer> FriendImg,
                       List<String> name,List<List<Integer>> itemPictures,List<String> friendContent,List<Integer> times){
        mFriendList=friendList;
        mFriendImg=FriendImg;
        mName=name;
        mItemPic=itemPictures;
        mFriendContent=friendContent;
        mTimes=times;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        return new NewsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {

        //点赞
        holder.imageStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.imageStar.setImageResource(R.drawable.star2);
            }
        });

        holder.friendImg.setImageResource(mFriendImg.get(position));
        holder.name.setText(mName.get(position));
        holder.friendContent.setText(mFriendContent.get(position));
        itempic=mItemPic.get(position);
        //Log.d("NewsAdapter","itempic number="+itempic.size());
        if(itempic.size()==1)
            holder.image1.setImageResource(mItemPic.get(position).get(0));
        else if(itempic.size()==2){
            holder.image1.setImageResource(mItemPic.get(position).get(0));
            holder.image2.setImageResource(mItemPic.get(position).get(1));
        }
        else if(itempic.size()==3){
            holder.image1.setImageResource(mItemPic.get(position).get(0));
            holder.image2.setImageResource(mItemPic.get(position).get(1));
            holder.image3.setImageResource(mItemPic.get(position).get(2));  //?
        }

        int time=mTimes.get(position);
        if(time>0&&time<9)
            holder.time.setText(time+"小时前");
        else if(time>10)
            holder.time.setText(time+"分钟前");
        else holder.time.setText("刚刚");
    }

    @Override
    public int getItemCount() {
        return mFriendList.size();
    }
}
