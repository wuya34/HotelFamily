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
        String price = getString(getColumnIndex(OrderTable.COL.PRICE));
        int deskNumber = getInt(getColumnIndex(OrderTable.COL.DESK_NUMBER));
        int isAvailable = getInt(getColumnIndex(OrderTable.COL.isAvailable));
        long date = getLong(getColumnIndex(OrderTable.COL.DATE));
        String phoneNumber = getString(getColumnIndex(OrderTable.COL.PHONE_NUMBER));
        int numberOfDiners = getInt(getColumnIndex(OrderTable.COL.NUMBER_OF_DINERS));

        Order order = new Order(UUID.fromString(uuid));
        order.setPrice(price);
        order.setDeskNumber(deskNumber);
        order.setDate(new Date(date));
        order.setAvailable(isAvailable==0);
        order.setPhoneNumber(phoneNumber);
        order.setNumberOfDiners(numberOfDiners);
        return order;
    }
}
