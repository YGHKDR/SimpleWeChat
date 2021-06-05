package com.example.simplewechat.ui.message;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.simplewechat.ChatActivity;
import com.example.simplewechat.R;
import com.example.simplewechat.component.FriendAdapter;
import com.example.simplewechat.domain.Friends;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MessageFragment extends Fragment {

    private List<Friends> friendsList=new ArrayList<>();

    private MessageViewModel mViewModel;

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.message_fragment, container, false);
        initData();
        FriendAdapter adapter=new FriendAdapter(getActivity(),R.layout.friend_item,friendsList);
        ListView listView=(ListView)view.findViewById( R.id.list_view);
        listView.setAdapter(adapter);
/*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Friends friend=friendsList.get(position);
                String name=friend.getName();
                //进入对话框
                Intent intent=new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });*/
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MessageViewModel.class);
        // TODO: Use the ViewModel
    }

    private void initData(){
        friendsList.add(new Friends("王川",R.drawable.p1));
        friendsList.add(new Friends("小李",R.drawable.p2));
        friendsList.add(new Friends("二次元猫猫",R.drawable.p3));
        friendsList.add(new Friends("小猫",R.drawable.p4));
        friendsList.add(new Friends("傻狗",R.drawable.p5));
        friendsList.add(new Friends("小羊",R.drawable.p6));
        friendsList.add(new Friends("傻兔",R.drawable.p7));
        friendsList.add(new Friends("一个人",R.drawable.p8));
        friendsList.add(new Friends("一只羊",R.drawable.p9));
        friendsList.add(new Friends("Cindy",R.drawable.p10));
        friendsList.add(new Friends("Lady Yang",R.drawable.p11));
        friendsList.add(new Friends("momo",R.drawable.p12));
        friendsList.add(new Friends("朵",R.drawable.p13));
        friendsList.add(new Friends("皇后",R.drawable.p14));
        friendsList.add(new Friends("杠杠",R.drawable.p15));

    }
}