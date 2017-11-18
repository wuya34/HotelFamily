package com.example.amyas.hotelfamily.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.amyas.hotelfamily.db.OrderBaseHelper;
import com.example.amyas.hotelfamily.db.OrderCursorWrapper;
import com.example.amyas.hotelfamily.db.OrderDbSchema.OrderTable;

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

    private static ContentValues getContentValues(Order order) {
        ContentValues values = new ContentValues();
        values.put(OrderTable.COL.UUID, order.getUUID().toString());
        values.put(OrderTable.COL.DATE, order.getDate().getTime());
        values.put(OrderTable.COL.DESK_NUMBER, order.getDeskNumber());
        values.put(OrderTable.COL.isAvailable, order.isAvailable()?0:1);
        values.put(OrderTable.COL.PRICE, order.getPrice());
        values.put(OrderTable.COL.NUMBER_OF_DINERS, order.getNumberOfDiners());
        values.put(OrderTable.COL.PHONE_NUMBER, order.getPhoneNumber());
        return values;
    }

    public List<Order> getOrderList() {
        List<Order> orders = new ArrayList<>();
        OrderCursorWrapper cursorWrapper = queryOrders(null, null);
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()){
                orders.add(cursorWrapper.getOrder());
                cursorWrapper.moveToNext();
            }
        }finally {
            cursorWrapper.close();
        }
        return orders;
    }

    public Order getOrder(UUID uuid) {
        OrderCursorWrapper wrapper = queryOrders(OrderTable.COL.UUID+ " = ?",
                new String[]{uuid.toString()});
        try {
            if (wrapper.getCount()==0){
                return null;
            }
            wrapper.moveToFirst();
            return wrapper.getOrder();
        }finally {
            wrapper.close();
        }
    }
    public void addOrder(Order order){
        ContentValues values = getContentValues(order);
        mDatabase.insert(OrderTable.NAME, null, values);
    }
    public void updateOrder(Order order){
        ContentValues values = getContentValues(order);
        String uuid = order.getUUID().toString();
        mDatabase.update(OrderTable.NAME, values, OrderTable.COL.UUID+ " = ?",
                new String[]{uuid});
    }
    private OrderCursorWrapper queryOrders(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                OrderTable.NAME,
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
