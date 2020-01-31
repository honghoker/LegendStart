package com.example.textsqlite;

import android.provider.BaseColumns;

public class GroceryContract {
    public static final class GrocertEntry implements BaseColumns{
        public static final String TABLE_NAME = "groceryList";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_DETAILADDRESS = "detailAddress";
        public static final String COLUM_TIMESTAMP = "timestamp";
    }
}
