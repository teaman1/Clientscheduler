package com.bignerdranch.android.clientscheduler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by teaman1 on 9/25/2016.
 */
public class customerhelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "customerhelper.db";

    public customerhelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + CustomerTable.Customer.NAME +"(" +
                "_id integer primary key autoincrement, " +
                CustomerTable.Customer.Cols.UUID + "," +
                CustomerTable.Customer.Cols.TITLE + ", " +
                CustomerTable.Customer.Cols.NAME +
                ")"
        );
    }
     @Override
     public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

     }

}

