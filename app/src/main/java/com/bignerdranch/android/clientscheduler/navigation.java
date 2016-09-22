package com.bignerdranch.android.clientscheduler;

import android.support.v4.app.Fragment;

/**
 * Created by teaman1 on 9/22/2016.
 */
public class navigation extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new navigationfragment();
    }
}
