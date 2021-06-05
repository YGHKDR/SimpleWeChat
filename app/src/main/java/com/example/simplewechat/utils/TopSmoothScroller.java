package com.example.simplewechat.utils;

import android.content.Context;

import androidx.recyclerview.widget.LinearSmoothScroller;

//RecyclerView中点击SlideBar跳转到相应位置的控件
public class TopSmoothScroller extends LinearSmoothScroller {
    public TopSmoothScroller(Context context) {
        super(context);
    }
    @Override
    protected int getHorizontalSnapPreference() {
        return SNAP_TO_START;//具体见源码注释
    }
    @Override
    protected int getVerticalSnapPreference() {
        return SNAP_TO_START;//具体见源码注释
    }
}