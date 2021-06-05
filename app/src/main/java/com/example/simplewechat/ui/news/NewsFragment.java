package com.example.simplewechat.ui.news;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.simplewechat.MakeMomentActivity;
import com.example.simplewechat.R;
import com.example.simplewechat.component.NewsAdapter;
import com.example.simplewechat.domain.Friends;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {
    private static String setStr="";
    private List<Friends> mFriendList;
    private List<Integer> mFriendImg;
    private List<String> mName;
    private List<String> mFriendContent;
    private List<List<Integer>> mItemPic;
    private List<Integer> itempic;
    private List<Integer> mTimes;
    private static NewsAdapter newsAdapter;
    private static boolean flag=false; //当自己发布朋友圈时，改为true
    private ImageView goMakeMoment;

    private NewsViewModel mViewModel;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.news_fragment, container, false);

        //折叠状态和非折叠状态
        AppBarLayout appBarLayout = view.findViewById(R.id.appBar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                CollapsingToolbarLayout collapsingToolbar =view.findViewById(R.id.collapsing_toolbar);
                int color = Color.argb(200,0,0,0);
                collapsingToolbar.setCollapsedTitleTextColor(color);
                ImageView imageView = view.findViewById(R.id.image1);  //头像
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) { // 折叠状态
                    collapsingToolbar.setTitle("朋友圈");
                    imageView.setVisibility(View.GONE); //折叠了头像也不可见
                } else { // 非折叠状态
                    collapsingToolbar.setTitle("");
                    imageView.setVisibility(View.VISIBLE);
                }
            }
        });

        initFriends();
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.news_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        newsAdapter=new NewsAdapter(mFriendList,mFriendImg,mName,mItemPic,mFriendContent,mTimes);
        recyclerView.setAdapter(newsAdapter);

        //点击相机图标跳转到发朋友圈界面
        goMakeMoment=view.findViewById(R.id.goMakeMoment);
        goMakeMoment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), MakeMomentActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        // TODO: Use the ViewModel
    }

    private void initFriends(){
        mFriendList=new ArrayList<>();
        if(flag)
            mFriendList.add(new Friends("泡泡",R.drawable.p16));
        mFriendList.add(new Friends("王川",R.drawable.p1));
        mFriendList.add(new Friends("小李",R.drawable.p2));
        mFriendList.add(new Friends("二次元猫猫",R.drawable.p3));
        mFriendList.add(new Friends("小猫",R.drawable.p4));
        mFriendList.add(new Friends("傻狗",R.drawable.p5));
        mFriendList.add(new Friends("一只羊",R.drawable.p9));
        mFriendList.add(new Friends("Cindy",R.drawable.p10));
        mFriendList.add(new Friends("Lady Yang",R.drawable.p11));
        //mFriendList.add(new Friends("杠杠",R.drawable.p15));

        mName=new ArrayList<>();
        if(flag)
            mName.add("泡泡");
        mName.add("王川");
        mName.add("小李");
        mName.add("二次元猫猫");
        mName.add("小猫");
        mName.add("傻狗");
        mName.add("一只羊");
        mName.add("Cindy");
        mName.add("Lady Yang");
        //mName.add("杠杠");

        mFriendImg=new ArrayList<>();
        if(flag)
            mFriendImg.add(R.drawable.p16);
        mFriendImg.add(R.drawable.p1);
        mFriendImg.add(R.drawable.p2);
        mFriendImg.add(R.drawable.p3);
        mFriendImg.add(R.drawable.p4);
        mFriendImg.add(R.drawable.p5);
        mFriendImg.add(R.drawable.p9);
        mFriendImg.add(R.drawable.p10);
        mFriendImg.add(R.drawable.p11);
        //mFriendImg.add(R.drawable.p15);

        mTimes=new ArrayList<>();
        if(flag)
            mTimes.add(0);
        mTimes.add(25);
        mTimes.add(34);
        mTimes.add(50);
        mTimes.add(1);
        mTimes.add(2);
        mTimes.add(5);
        mTimes.add(7);
        mTimes.add(9);
        //mTimes.add(10);

        mItemPic=new ArrayList<>();
        itempic=new ArrayList<>();
        if(flag)
            mItemPic.add(itempic);

        itempic=new ArrayList<>();
        itempic.add(R.drawable.pyq1);
        mItemPic.add(itempic);

        itempic=new ArrayList<>();
        itempic.add(R.drawable.pyq2_1);
        itempic.add(R.drawable.pyq2_2);
        mItemPic.add(itempic);

        itempic=new ArrayList<>();
        itempic.add(R.drawable.pyq3_1);
        itempic.add(R.drawable.pyq3_2);
        mItemPic.add(itempic);

        itempic=new ArrayList<>();
        itempic.add(R.drawable.pyq4_1);
        itempic.add(R.drawable.pyq4_2);
        itempic.add(R.drawable.pyq4_3);
        mItemPic.add(itempic);

        itempic=new ArrayList<>();
        itempic.add(R.drawable.pyq5_1);
        itempic.add(R.drawable.pyq5_2);
        mItemPic.add(itempic);

        itempic=new ArrayList<>();
        itempic.add(R.drawable.pyq6);
        mItemPic.add(itempic);

        itempic=new ArrayList<>();
        itempic.add(R.drawable.pyq7);
        mItemPic.add(itempic);

        itempic=new ArrayList<>();
        itempic.add(R.drawable.pyq8_1);
        itempic.add(R.drawable.pyq8_2);
        mItemPic.add(itempic);

        mFriendContent=new ArrayList<>();
        if(flag)
            mFriendContent.add(setStr);//发布的内容暂时固定
        mFriendContent.add("熬夜了两天给论文降重，今天终于可以提交了（困）");
        mFriendContent.add("今天吃冰激凌的时候，冰激凌掉地上了");
        mFriendContent.add("独自去了竹林探险。");
        mFriendContent.add("在厦门看海。");
        mFriendContent.add("我侄子可爱吗？");
        mFriendContent.add("我妈翻出来的老照片（笑哭）");
        mFriendContent.add("原来今天是周六，我说怎么赶到教室没人呢。");
        mFriendContent.add("我有狗子啦！");
        //mFriendContent.add("我是一只绵羊 今天我剪了毛 然后我失棉了");
    }

    public static void setContent(String str){
        setStr=str;
    }

    public static void update(){
        flag=true;
        newsAdapter.notifyDataSetChanged();
    }
}