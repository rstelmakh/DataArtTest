package com.rstelmakh.dataarttest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by roman on 3/15/2015.
 */
public class StoresDBHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String DOUBLE_TYPE = " REAL";

    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + StoresContract.StoreEntry.TABLE_NAME + " (" +
                    StoresContract.StoreEntry._ID + " INTEGER PRIMARY KEY," +
                    StoresContract.StoreEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    StoresContract.StoreEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    StoresContract.StoreEntry.COLUMN_NAME_ADDRESS + TEXT_TYPE + COMMA_SEP +
                    StoresContract.StoreEntry.COLUMN_NAME_PHONE + TEXT_TYPE + COMMA_SEP +
                    StoresContract.StoreEntry.COLUMN_NAME_LAT + INTEGER_TYPE + COMMA_SEP +
                    StoresContract.StoreEntry.COLUMN_NAME_LON + INTEGER_TYPE +
            " )";

    private static final String SQL_CREATE_INSTRUMENTS =
            "CREATE TABLE " + StoresContract.InstrumentEntry.TABLE_NAME + " (" +
                    StoresContract.InstrumentEntry._ID + " INTEGER PRIMARY KEY," +
                    StoresContract.InstrumentEntry.COLUMN_NAME_STORE_ID + INTEGER_TYPE + COMMA_SEP +
                    StoresContract.InstrumentEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    StoresContract.InstrumentEntry.COLUMN_NAME_BRAND + TEXT_TYPE + COMMA_SEP +
                    StoresContract.InstrumentEntry.COLUMN_NAME_MODEL + TEXT_TYPE + COMMA_SEP +
                    StoresContract.InstrumentEntry.COLUMN_NAME_TYPE + TEXT_TYPE + COMMA_SEP +
                    StoresContract.InstrumentEntry.COLUMN_NAME_QUANTITY + INTEGER_TYPE + COMMA_SEP +
                    StoresContract.InstrumentEntry.COLUMN_NAME_PRICE + DOUBLE_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + StoresContract.StoreEntry.TABLE_NAME;
    private static final String SQL_CLEAR_ENTRIES = "DELETE FROM " + StoresContract.StoreEntry.TABLE_NAME;
    private static final String SQL_CLEAR_INSTRUMENTS = "DELETE FROM " + StoresContract.InstrumentEntry.TABLE_NAME;
    private static final String SQL_CLEAR_INSTRUMENTS_WHERE = "DELETE FROM " + StoresContract.InstrumentEntry.TABLE_NAME +
            " WHERE " + StoresContract.InstrumentEntry.COLUMN_NAME_STORE_ID + " = %s";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "stores.db";

    public StoresDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_INSTRUMENTS);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void clearDB(){
        getWritableDatabase().execSQL(SQL_CLEAR_ENTRIES);
        getWritableDatabase().execSQL(SQL_CLEAR_INSTRUMENTS);
    }

    public void clearStores(){
        getWritableDatabase().execSQL(SQL_CLEAR_ENTRIES);
    }

    public void clearInstruments(int storeId){
        getWritableDatabase().execSQL(String.format(SQL_CLEAR_INSTRUMENTS_WHERE, storeId));
    }

    public void clearInstruments(){
        getWritableDatabase().execSQL(SQL_CLEAR_INSTRUMENTS);
    }
}
