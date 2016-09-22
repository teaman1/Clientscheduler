package com.bignerdranch.android.clientscheduler;

import android.app.ListActivity;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by teaman1 on 9/15/2016.
 */
public class Crimelab {
    private static Crimelab sCrimelab;
    private List<customerinfo> mCrimes;

    public static Crimelab get(Context context) {
        if (sCrimelab == null) {
            sCrimelab = new Crimelab(context);
        }
            return sCrimelab;
    }
    private Crimelab(Context context) {
        mCrimes = new ArrayList<>();
        for (int i = 0; i<100;i++){
            customerinfo crime = new customerinfo();
            crime.setTitle("Customer #" + i);
            mCrimes.add(crime);
        }
    }
    public List<customerinfo> getCrimes() {
        return mCrimes;
    }

    public customerinfo getCrime(UUID id) {
        for (customerinfo crime : mCrimes) {
            if (crime.getId().equals(id)) {
            }
        }
        return null;
    }


}
