package com.example.amyas.hotelfamily.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.amyas.hotelfamily.db.OrderDbSchema.DeskOrderSchema;
import com.example.amyas.hotelfamily.db.OrderDbSchema.DeskTableSchema;

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
        db.execSQL("create table "+ DeskOrderSchema.NAME+"("+
        " _id integer primary key autoincrement, "
                + DeskOrderSchema.COL.UUID +", "
                + DeskOrderSchema.COL.DESK_NUMBER+", "
                + DeskOrderSchema.COL.DATE+", "
                + DeskOrderSchema.COL.RELATIVE_STRING+" ,"
                + DeskOrderSchema.COL.PRICE+")");

        db.execSQL("create table "+ DeskTableSchema.NAME+"("
                + " _id integer primary key autoincrement, "
                + DeskTableSchema.COL.UUID +", "
                + DeskTableSchema.COL.DESK_NUMBER+", "
                + DeskTableSchema.COL.isAvailable+", "
                + DeskTableSchema.COL.PHONE_NUMBER +", "
                + DeskTableSchema.COL.NUMBER_OF_DINERS+" ,"
                + DeskTableSchema.COL.RELATIVE_STRING
                +")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
