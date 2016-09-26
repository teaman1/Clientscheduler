package com.bignerdranch.android.clientscheduler;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by teaman1 on 9/22/2016.
 */
public class navigationfragment extends Fragment {
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;
    private Button customerreceipt;
    private Button sessionlist;
    private Button customerlist;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.navgation_menu, container, false);

        customerreceipt = (Button)v.findViewById(R.id.customerreceipt);
        customerreceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Button Pressed", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), customerreceipt.class);
                startActivity(i);
            }
        });

        sessionlist = (Button)v.findViewById(R.id.sessionlist);
        sessionlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Button Pressed", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), newcustomersession.class);
                startActivity(i);
            }
        });

        customerlist = (Button)v.findViewById(R.id.customerlist);
        customerlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Button Pressed", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), customer.class);
                startActivity(i);
            }
        });
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.options, menu);

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_item_new_crime:
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.setTargetFragment(navigationfragment.this, REQUEST_DATE);
                dialog.show(manager,DIALOG_DATE);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK){
            return;


        }
        if(requestCode==REQUEST_DATE){
            Intent i = new Intent(getActivity(), login.class);
            startActivity(i);
        }
    }
}


