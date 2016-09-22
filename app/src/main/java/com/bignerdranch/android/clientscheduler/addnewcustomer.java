package com.bignerdranch.android.clientscheduler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

/**
 * Created by teaman1 on 8/30/2016.FragmentActivity
 */
public class addnewcustomer extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new fragment();
    }

}



