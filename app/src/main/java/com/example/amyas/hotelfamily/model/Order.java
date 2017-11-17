package com.example.amyas.hotelfamily.model;

import java.util.Date;
import java.util.UUID;

/**
 * author: Amyas
 * date: 2017/11/16
 */

public class Order {
    private UUID mUUID;
    private float price;
    private int desk_number;
    private Date mDate;
    private boolean isAvailable;

    public Order() {
        this(UUID.randomUUID());
    }

    public Order(UUID uuid) {
        mUUID = uuid;
        mDate = new Date();
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDesk_number() {
        return desk_number;
    }

    public void setDesk_number(int desk_number) {
        this.desk_number = desk_number;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}
