package com.example.amyas.hotelfamily.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.amyas.hotelfamily.db.OrderBaseHelper;
import com.example.amyas.hotelfamily.db.OrderCursorWrapper;
import com.example.amyas.hotelfamily.db.OrderDbSchema;
import com.example.amyas.hotelfamily.db.OrderDbSchema.DeskOrderSchema;
import com.example.amyas.hotelfamily.db.OrderDbSchema.DeskTableSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * author: Amyas
 * date: 2017/11/16
 */

public class OrderLab {
    private static OrderLab sOrderLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    private OrderLab(Context context) {
        mContext = context;
        mDatabase = new OrderBaseHelper(context).getWritableDatabase();
    }

    public static OrderLab get(Context context) {
        if (sOrderLab == null) {
            sOrderLab = new OrderLab(context);
        }
        return sOrderLab;
    }

    private static ContentValues getContentValues(DeskOrder deskOrder) {
        ContentValues values = new ContentValues();
        values.put(DeskOrderSchema.COL.UUID, deskOrder.getUUID().toString());
        values.put(DeskOrderSchema.COL.DATE, deskOrder.getDate().getTime());
        values.put(DeskOrderSchema.COL.DESK_NUMBER, deskOrder.getDeskNumber());
        values.put(DeskOrderSchema.COL.PRICE, deskOrder.getPrice());
        values.put(DeskOrderSchema.COL.RELATIVE_STRING, deskOrder.getRelativeString());
        return values;
    }
    /*
    isAvailable  true: 0  false: 1
    */
    private static ContentValues getContentValues(DeskTable deskTable) {
        ContentValues values = new ContentValues();
        values.put(DeskTableSchema.COL.UUID, deskTable.getUUID().toString());
        values.put(DeskTableSchema.COL.DESK_NUMBER, deskTable.getDeskNumber());
        values.put(DeskTableSchema.COL.isAvailable, deskTable.isAvailable() ? 0 : 1);
        values.put(DeskTableSchema.COL.NUMBER_OF_DINERS, deskTable.getNumberOfDiners());
        values.put(DeskTableSchema.COL.PHONE_NUMBER, deskTable.getPhoneNumber());
        values.put(DeskTableSchema.COL.RELATIVE_STRING, deskTable.getRelativeString());
        return values;
    }

    public List<DeskOrder> getOrderList() {
        ArrayList<DeskOrder> deskOrders = new ArrayList<>();
        OrderCursorWrapper cursorWrapper = query(null, null, OrderDbSchema.DeskOrderSchema.NAME);
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                deskOrders.add(cursorWrapper.getOrder());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }
        return deskOrders;
    }

    public List<DeskTable> getTableList() {
        ArrayList<DeskTable> deskTables = new ArrayList<>();
        OrderCursorWrapper cursorWrapper = query(null, null, DeskTableSchema.NAME);
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                deskTables.add(cursorWrapper.getTable());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }
        return deskTables;
    }

    public DeskOrder getOrder(UUID uuid) {
        OrderCursorWrapper wrapper = query(OrderDbSchema.DeskOrderSchema.COL.UUID + " = ?",
                new String[]{uuid.toString()}, OrderDbSchema.DeskOrderSchema.NAME);
        try {
            if (wrapper.getCount() == 0) {
                return null;
            }
            wrapper.moveToFirst();
            return wrapper.getOrder();
        } finally {
            wrapper.close();
        }
    }

    public DeskTable getTable(UUID uuid) {
        OrderCursorWrapper wrapper = query(DeskTableSchema.COL.UUID + " = ?",
                new String[]{uuid.toString()}, DeskTableSchema.NAME);
        try {
            if (wrapper.getCount() == 0) {
                return null;
            }
            wrapper.moveToFirst();
            return wrapper.getTable();
        } finally {
            wrapper.close();
        }
    }


    public void addOrder(DeskOrder deskOrder) {
        ContentValues values = getContentValues(deskOrder);
        mDatabase.insert(OrderDbSchema.DeskOrderSchema.NAME, null, values);
    }

    public void addTable(DeskTable deskTable) {
        ContentValues values = getContentValues(deskTable);
        mDatabase.insert(DeskTableSchema.NAME, null, values);
    }

    public void updateOrder(DeskOrder deskOrder) {
        ContentValues values = getContentValues(deskOrder);
        String uuid = deskOrder.getUUID().toString();
        mDatabase.update(OrderDbSchema.DeskOrderSchema.NAME, values, OrderDbSchema.DeskOrderSchema.COL.UUID + " = ?",
                new String[]{uuid});
    }

    public void updateTable(DeskTable deskTable) {
        ContentValues values = getContentValues(deskTable);
        String uuid = deskTable.getUUID().toString();
        mDatabase.update(DeskTableSchema.NAME, values, DeskTableSchema.COL.UUID + " = ?",
                new String[]{uuid});
    }

    public DeskOrder orderQueryByDeskNumber(String deskNumber){
        OrderCursorWrapper wrapper = query(DeskOrderSchema.COL.DESK_NUMBER+ " = ?",
                new String[]{deskNumber}, DeskOrderSchema.NAME);
        try {
            if (wrapper.getCount() == 0) {
                return null;
            }
            wrapper.moveToFirst();
            return wrapper.getOrder();
        } finally {
            wrapper.close();
        }
    }

    public DeskTable tableQueryByDeskNumber(String deskNumber){
        OrderCursorWrapper wrapper = query(DeskOrderSchema.COL.DESK_NUMBER+ " = ?",
                new String[]{deskNumber}, DeskTableSchema.NAME);
        try {
            if (wrapper.getCount() == 0) {
                return null;
            }
            wrapper.moveToFirst();
            return wrapper.getTable();
        } finally {
            wrapper.close();
        }
    }

    public DeskOrder orderQueryByRelativeString(String relativeString){
        OrderCursorWrapper wrapper = query(DeskOrderSchema.COL.RELATIVE_STRING+ " = ?",
                new String[]{relativeString}, DeskOrderSchema.NAME);
        try {
            if (wrapper.getCount() == 0) {
                return null;
            }
            wrapper.moveToFirst();
            return wrapper.getOrder();
        } finally {
            wrapper.close();
        }
    }

    public DeskTable tableQueryByRelativeString(String relativeString){
        OrderCursorWrapper wrapper = query(DeskOrderSchema.COL.RELATIVE_STRING+ " = ?",
                new String[]{relativeString}, DeskTableSchema.NAME);
        try {
            if (wrapper.getCount() == 0) {
                return null;
            }
            wrapper.moveToFirst();
            return wrapper.getTable();
        } finally {
            wrapper.close();
        }
    }

    private OrderCursorWrapper query(String whereClause, String[] whereArgs, String tableName) {
        Cursor cursor = mDatabase.query(
                tableName,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new OrderCursorWrapper(cursor);
    }

}
