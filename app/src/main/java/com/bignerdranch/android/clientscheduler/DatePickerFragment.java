package com.bignerdranch.android.clientscheduler;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.Date;

/**
 * Created by teaman1 on 9/8/2016.
 */
public class DatePickerFragment extends DialogFragment {
    public static final String EXTRA_DATE =
            "com.bignerdranch.android.logoff.date";

    private void sendResult(int resultCode, Date date) {
        if(getTargetFragment()==null){
            return;


        }
    Intent intent = new Intent();
    intent.putExtra(EXTRA_DATE, date);

    getTargetFragment()
            .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        return new AlertDialog.Builder(getActivity())
                .setTitle("Logging You Off")
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Date date = new Date();
                                sendResult(Activity.RESULT_OK, date);
                            }
                        })
                .create();
    }
}
