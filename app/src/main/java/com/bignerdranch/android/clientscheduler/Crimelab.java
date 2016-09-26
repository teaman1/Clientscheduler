package com.bignerdranch.android.clientscheduler;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by teaman1 on 9/15/2016.
 */
public class Crimelab {
    private static Crimelab sCrimelab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static Crimelab get(Context context) {
        if (sCrimelab == null) {
            sCrimelab = new Crimelab(context);
        }
            return sCrimelab;
    }
    private Crimelab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new customerhelper(mContext)
                .getWritableDatabase();


    }
    public void addcustomer(customerinfo c){
        ContentValues values = getContentValues(c);
        mDatabase.insert(CustomerTable.Customer.NAME, null, values);
    }
    public List<customerinfo> getCrimes() {
        List<customerinfo> customerinfos = new ArrayList<>();

        CustomerCursorWrapper cursor = querycustomer(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                customerinfos.add(cursor.customer());
                cursor.moveToNext();

            }

        }finally{
            cursor.close();
        }
        return customerinfos;
    }


    public customerinfo getCrime(UUID id) {
        CustomerCursorWrapper cursor = querycustomer(
                CustomerTable.Customer.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return  cursor.customer();
        } finally {
            cursor.close();
        }
    }
    public void updatecustomer(customerinfo customer){
        String uuidString = customer.getId().toString();
        ContentValues values = getContentValues(customer);

        mDatabase.update(CustomerTable.Customer.NAME,values,
                CustomerTable.Customer.Cols.UUID + " = ?",
                new String[]{uuidString});
    }
private static ContentValues getContentValues(customerinfo customer){
    ContentValues values = new ContentValues();
    values.put(CustomerTable.Customer.Cols.UUID, customer.getId().toString());
    values.put(CustomerTable.Customer.Cols.TITLE, customer.getTitle());
    values.put(CustomerTable.Customer.Cols.NAME,customer.getClientlist());
    return values;
}
    private CustomerCursorWrapper querycustomer(String whereclause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CustomerTable.Customer.NAME,
                null,
                whereclause,
                whereArgs,
                null,
                null,
                null

        );
        return new CustomerCursorWrapper(cursor);
    }
}
