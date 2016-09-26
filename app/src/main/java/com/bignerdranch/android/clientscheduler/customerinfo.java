package com.bignerdranch.android.clientscheduler;

import java.util.UUID;

/**
 * Created by teaman1 on 9/12/2016.
 */
public class customerinfo {

    private UUID mId;
    private String mTitle;
    private String clientlist;

    public customerinfo() {
        this(UUID.randomUUID());
    }
    public customerinfo(UUID id){
        mId = id;
    }
public UUID getId() {
        return mId;
}
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) {
        mTitle = title;
    }

    public String getClientlist() {
        return clientlist;
    }

    public void setClientlist(String clientlist) {
        this.clientlist = clientlist;
    }
}








