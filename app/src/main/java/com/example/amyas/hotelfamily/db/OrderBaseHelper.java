package com.example.amyas.hotelfamily.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.amyas.hotelfamily.db.OrderDbSchema.OrderTable;

/**
 * author: Amyas
 * date: 2017/11/17
 */

public class OrderBaseHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "orderBase.db";
    public OrderBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ OrderTable.NAME+"("+
        " _id integer primary key autoincrement, "+ OrderTable.COL.UUID
                +", "+OrderTable.COL.DESK_NUMBER+", "+OrderTable.COL.isAvailable+", "
        +OrderTable.COL.DATE+", "+ OrderTable.COL.PRICE+", "+OrderTable.COL.PHONE_NUMBER
                +", "+OrderTable.COL.NUMBER_OF_DINERS+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
