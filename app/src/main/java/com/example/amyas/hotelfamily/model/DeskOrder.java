package com.example.amyas.hotelfamily.model;

import java.util.Date;
import java.util.UUID;

/**
 * author: Amyas
 * date: 2017/11/16
 */

public class DeskOrder {
    private UUID mUUID;
    private String price;
    private String deskNumber;
    private Date mDate;
    private String relativeString;

    public DeskOrder() {
        this(UUID.randomUUID());
    }

    public DeskOrder(UUID uuid) {
        mUUID = uuid;
        mDate = new Date();
    }

    public String getRelativeString() {
        return relativeString;
    }

    public void setRelativeString(String relativeString) {
        this.relativeString = relativeString;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDeskNumber() {
        return deskNumber;
    }

    public void setDeskNumber(String deskNumber) {
        this.deskNumber = deskNumber;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}
