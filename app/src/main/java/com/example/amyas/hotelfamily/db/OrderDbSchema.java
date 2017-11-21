package com.example.amyas.hotelfamily.db;

/**
 * author: Amyas
 * date: 2017/11/17
 */

public class OrderDbSchema {
    public static final class DeskOrderSchema {
        public static final String NAME = "deskOrder";
        public static final class COL{
            public static final String UUID = "uuid";
            public static final String PRICE = "price";
            public static final String DESK_NUMBER = "desk_number";
            public static final String DATE = "date";
            public static final String RELATIVE_STRING = "relative_string";
//            public static final String isAvailable = "is_available";
//            public static final String NUMBER_OF_DINERS = "number_of_diners";
//            public static final String PHONE_NUMBER = "phone_number";
        }
    }
    public static final class DeskTableSchema {
        public static final String NAME = "deskTable";
        public static final class COL{
            public static final String UUID = "uuid";
            public static final String DESK_NUMBER = "desk_number";
            public static final String isAvailable = "is_available";
            public static final String NUMBER_OF_DINERS = "number_of_diners";
            public static final String PHONE_NUMBER = "phone_number";
            public static final String RELATIVE_STRING = "relative_string";
        }
    }
}
