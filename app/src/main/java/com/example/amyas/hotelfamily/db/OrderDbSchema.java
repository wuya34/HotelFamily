package com.example.amyas.hotelfamily.db;

/**
 * author: Amyas
 * date: 2017/11/17
 */

public class OrderDbSchema {
    public static final class OrderTable{
        public static final String NAME = "orders";
        public static final class COL{
            public static final String UUID = "uuid";
            public static final String PRICE = "price";
            public static final String DESK_NUMBER = "desk_number";
            public static final String DATE = "date";
            public static final String isAvailable = "is_available";
        }
    }
}
