package com.example.simplewechat.domain;

import com.example.simplewechat.component.FriendAdapter;
import com.example.simplewechat.utils.Cn2Spell;

import java.io.Serializable;

public class Friends implements Serializable, Comparable<Friends> {
    private String name;
    private int friendId;
    private String pinyin; //拼音
    private String start; //首字母

    public Friends(String name,int imageId){
        this.name=name;
        friendId=imageId;
        pinyin= Cn2Spell.getPinYin(name);
        start=pinyin.substring(0,1).toUpperCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    @Override
    public int compareTo(Friends friends) {
        if (start.equals("#") && !friends.getStart().equals("#")) {
            return 1;
        } else if (!start.equals("#") && friends.getStart().equals("#")) {
            return -1;
        } else {
            return pinyin.compareToIgnoreCase(friends.getPinyin());
        }
    }

}
