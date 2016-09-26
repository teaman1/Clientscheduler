package com.bignerdranch.android.clientscheduler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.UUID;

/**
 * Created by teaman1 on 8/30/2016.FragmentActivity
 */
public class addnewcustomer extends SingleFragmentActivity {
    private static final String EXTRA_CUSTOMER_ID=
            "com.bignerdranch.android.clientscheduler.customer_id";
    public static Intent newIntent(Context packageContext, UUID customerID){
        Intent intent = new Intent(packageContext, addnewcustomer.class);
        intent.putExtra(EXTRA_CUSTOMER_ID, customerID);
        return intent;

    }
    @Override
    protected Fragment createFragment(){
       UUID customerId = (UUID)getIntent()
               .getSerializableExtra(EXTRA_CUSTOMER_ID);
        return fragment.newInstance(customerId);
    }
}



