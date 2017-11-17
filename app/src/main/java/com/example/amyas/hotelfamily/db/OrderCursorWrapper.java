package com.example.amyas.hotelfamily.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.amyas.hotelfamily.db.OrderDbSchema.OrderTable;
import com.example.amyas.hotelfamily.model.Order;

import java.util.Date;
import java.util.UUID;

/**
 * author: Amyas
 * date: 2017/11/17
 */

public class OrderCursorWrapper extends CursorWrapper {
    public OrderCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Order getOrder(){
        String uuid = getString(getColumnIndex(OrderTable.COL.UUID));
        float price = getFloat(getColumnIndex(OrderTable.COL.PRICE));
        int desk_number = getInt(getColumnIndex(OrderTable.COL.DESK_NUMBER));
        int is_available = getInt(getColumnIndex(OrderTable.COL.isAvailable));
        long date = getLong(getColumnIndex(OrderTable.COL.DATE));

        Order order = new Order(UUID.fromString(uuid));
        order.setPrice(price);
        order.setDesk_number(desk_number);
        order.setDate(new Date(date));
        order.setAvailable(is_available==0);
        return order;
    }
}
