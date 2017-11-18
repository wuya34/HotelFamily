package com.example.amyas.hotelfamily.model;

import java.util.Date;
import java.util.UUID;

/**
 * author: Amyas
 * date: 2017/11/16
 */

public class Order {
    private UUID mUUID;
    private String price;
    private int deskNumber;
    private Date mDate;
    private boolean isAvailable;
    private String phoneNumber;
    private int numberOfDiners;

    public Order() {
        this(UUID.randomUUID());
    }

    public Order(UUID uuid) {
        mUUID = uuid;
        mDate = new Date();
    }

    public int getNumberOfDiners() {
        return numberOfDiners;
    }

    public void setNumberOfDiners(int numberOfDiners) {
        this.numberOfDiners = numberOfDiners;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getDeskNumber() {
        return deskNumber;
    }

    public void setDeskNumber(int deskNumber) {
        this.deskNumber = deskNumber;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}
