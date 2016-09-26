package com.bignerdranch.android.clientscheduler;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

/**
 * Created by teaman1 on 9/26/2016.
 */
public class CustomerCursorWrapper extends CursorWrapper {
    public CustomerCursorWrapper(Cursor cursor){
        super(cursor);
    }
    public customerinfo customer(){
        String uuidString = getString(getColumnIndex(CustomerTable.Customer.Cols.UUID));
        String title = getString(getColumnIndex(CustomerTable.Customer.Cols.TITLE));
        String name = getString(getColumnIndex(CustomerTable.Customer.Cols.NAME));

        customerinfo c = new customerinfo(UUID.fromString(uuidString));
        c.setTitle(title);
        c.setClientlist(name);
        return c;
    }
}
