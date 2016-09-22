package com.bignerdranch.android.clientscheduler;


import android.support.v4.app.Fragment;

/**
 * Created by teaman1 on 9/15/2016.
 */
public class customer extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new customerhelp();
    }
}
