package com.example.simplewechat.ui.friends;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dlong.rep.dlsidebar.DLSideBar;
import com.example.simplewechat.R;
import com.example.simplewechat.component.DirectoryAdapter;
import com.example.simplewechat.component.FriendAdapter;
import com.example.simplewechat.domain.Friends;
import com.example.simplewechat.utils.TopSmoothScroller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FriendsFragment extends Fragment {

    private LinearLayoutManager layoutManager;
    private DLSideBar sbIndex;
    RecyclerView friendView;
    DirectoryAdapter friendAdapter;
    List<Friends> friendsList=new ArrayList<>();;

    private FriendsViewModel mViewModel;

    public static FriendsFragment newInstance() {
        return new FriendsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.friends_fragment, container, false);
        initData();

        layoutManager=new LinearLayoutManager(getContext());
        friendView=view.findViewById(R.id.friendList);
        friendView.setLayoutManager(layoutManager);
        friendAdapter=new DirectoryAdapter(friendsList,getContext());
        friendView.setAdapter(friendAdapter);

        sbIndex=view.findViewById(R.id.sb_index);
        sbIndex.setOnTouchingLetterChangedListener(mSBTouchListener);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FriendsViewModel.class);
        // TODO: Use the ViewModel
    }

    /**
     * ??????index????????????
     */
    private DLSideBar.OnTouchingLetterChangedListener mSBTouchListener = new DLSideBar.OnTouchingLetterChangedListener() {
        @Override
        public void onTouchingLetterChanged(String str) {
            // ??????????????????A???Z
            // ????????????????????????
            //????????????????????????list???????????????position???????????????????????????position???
            //???????????????????????????????????????
            int position=0;
            for(int i=0;i<friendsList.size();i++){
                Friends temp=friendsList.get(i);
                position=i;
                if(temp.getStart()==str){
                    break;
                }
            }
            LinearSmoothScroller smoothScroller=new TopSmoothScroller(getActivity());
            smoothScroller.setTargetPosition(position);
            layoutManager.startSmoothScroll(smoothScroller);
        }
    };

    //????????????????????????????????????MessageFragment???FriendsFragment????????????
    private void initData(){
        friendsList.add(new Friends("??????",R.drawable.p1));
        friendsList.add(new Friends("??????",R.drawable.p2));
        friendsList.add(new Friends("???????????????",R.drawable.p3));
        friendsList.add(new Friends("??????",R.drawable.p4));
        friendsList.add(new Friends("??????",R.drawable.p5));
        friendsList.add(new Friends("??????",R.drawable.p6));
        friendsList.add(new Friends("??????",R.drawable.p7));
        friendsList.add(new Friends("?????????",R.drawable.p8));
        friendsList.add(new Friends("?????????",R.drawable.p9));
        friendsList.add(new Friends("Cindy",R.drawable.p10));
        friendsList.add(new Friends("Lady Yang",R.drawable.p11));
        friendsList.add(new Friends("momo",R.drawable.p12));
        friendsList.add(new Friends("???",R.drawable.p13));
        friendsList.add(new Friends("??????",R.drawable.p14));
        friendsList.add(new Friends("??????",R.drawable.p15));
        Collections.sort(friendsList);

    }
}