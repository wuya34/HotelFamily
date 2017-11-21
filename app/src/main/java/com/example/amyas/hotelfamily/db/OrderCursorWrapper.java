package com.example.amyas.hotelfamily.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.amyas.hotelfamily.db.OrderDbSchema.DeskTableSchema;
import com.example.amyas.hotelfamily.model.DeskOrder;
import com.example.amyas.hotelfamily.model.DeskTable;

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
    public DeskOrder getOrder(){
        String uuid = getString(getColumnIndex(OrderDbSchema.DeskOrderSchema.COL.UUID));
        String price = getString(getColumnIndex(OrderDbSchema.DeskOrderSchema.COL.PRICE));
        String deskNumber = getString(getColumnIndex(OrderDbSchema.DeskOrderSchema.COL.DESK_NUMBER));
        long date = getLong(getColumnIndex(OrderDbSchema.DeskOrderSchema.COL.DATE));
        String relativeString = getString(getColumnIndex(OrderDbSchema.DeskOrderSchema.COL.RELATIVE_STRING));

        DeskOrder deskOrder = new DeskOrder(UUID.fromString(uuid));
        deskOrder.setPrice(price);
        deskOrder.setDeskNumber(deskNumber);
        deskOrder.setDate(new Date(date));
        deskOrder.setRelativeString(relativeString);
        return deskOrder;
    }
    public DeskTable getTable(){
        String uuid = getString(getColumnIndex(DeskTableSchema.COL.UUID));
        String deskNumber = getString(getColumnIndex(DeskTableSchema.COL.DESK_NUMBER));
        int isAvailable = getInt(getColumnIndex(DeskTableSchema.COL.isAvailable));
        String phoneNumber = getString(getColumnIndex(DeskTableSchema.COL.PHONE_NUMBER));
        String numberOfDiners = getString(getColumnIndex(DeskTableSchema.COL.NUMBER_OF_DINERS));
        String relativeString = getString(getColumnIndex(DeskTableSchema.COL.RELATIVE_STRING));

        DeskTable deskTable = new DeskTable(UUID.fromString(uuid));
        deskTable.setDeskNumber(deskNumber);
        deskTable.setAvailable(isAvailable==0);
        deskTable.setPhoneNumber(phoneNumber);
        deskTable.setNumberOfDiners(numberOfDiners);
        deskTable.setRelativeString(relativeString);
        return deskTable;
    }
}
