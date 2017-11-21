package com.example.amyas.hotelfamily.model;

import java.util.UUID;

/**
 * author: Amyas
 * date: 2017/11/21
 */

public class DeskTable {
    private UUID mUUID;
    private boolean isAvailable;
    private String numberOfDiners;
    private String deskNumber;
    private String phoneNumber;
    private String relativeString;

    public DeskTable(UUID uuid) {
        mUUID = uuid;
    }

    public DeskTable() {
        this(UUID.randomUUID());
    }

    public String getRelativeString() {
        return relativeString;
    }

    public void setRelativeString(String relativeString) {
        this.relativeString = relativeString;
    }

    public String getDeskNumber() {
        return deskNumber;
    }

    public void setDeskNumber(String deskNumber) {
        this.deskNumber = deskNumber;
    }

    @Override
    public String toString() {
        return "DeskTableSchema{" +
                "mUUID=" + mUUID +
                ", isAvailable=" + isAvailable +
                ", numberOfDiners='" + numberOfDiners + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public UUID getUUID() {
        return mUUID;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getNumberOfDiners() {
        return numberOfDiners;
    }

    public void setNumberOfDiners(String numberOfDiners) {
        this.numberOfDiners = numberOfDiners;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
