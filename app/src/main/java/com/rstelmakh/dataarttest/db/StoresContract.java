package com.rstelmakh.dataarttest.db;

import android.provider.BaseColumns;

/**
 * Created by roman on 3/15/2015.
 */
public final class StoresContract {
    public StoresContract() {}

    /* Inner class that defines the table contents */
    public static abstract class StoreEntry implements BaseColumns {
        public static final String TABLE_NAME = "stores";

        public static final String COLUMN_NAME_ENTRY_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_LAT = "latitude";
        public static final String COLUMN_NAME_LON = "longitude";
    }

    /* Inner class that defines the table contents */
    public static abstract class InstrumentEntry implements BaseColumns {
        public static final String TABLE_NAME = "instruments";

        public static final String COLUMN_NAME_ENTRY_ID = "id";
        public static final String COLUMN_NAME_STORE_ID = "store_id";
        public static final String COLUMN_NAME_BRAND = "brand";
        public static final String COLUMN_NAME_MODEL = "model";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_QUANTITY = "quantity";
    }

}
