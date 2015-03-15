package com.rstelmakh.dataarttest.db;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by roman on 3/15/2015.
 */
public class StoresProvider extends ContentProvider {

    private StoresDBHelper dbHelper;

    private static final String AUTHORITY = "com.rstelmakh.dataarttest.contentprovider";

    private static final int STORE = 100;
    private static final int STORE_ID = 101;
    private static final int INSTRUMENT = 200;
    private static final int INSTRUMENT_ID = 201;

    private static final String STORES_PATH = "stores";
    private static final String INSTRUMENT_PATH = "instruments";

    public static final Uri STORES_URI = Uri.parse("content://" + AUTHORITY + "/" + STORES_PATH);
    public static final Uri INSTRUMENT_URI = Uri.parse("content://" + AUTHORITY + "/" + INSTRUMENT_PATH);

//    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/repo";
//    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/repo";

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, STORES_PATH, STORE);
        sURIMatcher.addURI(AUTHORITY, STORES_PATH + "/#", STORE_ID);
        sURIMatcher.addURI(AUTHORITY, INSTRUMENT_PATH, INSTRUMENT);
        sURIMatcher.addURI(AUTHORITY, INSTRUMENT_PATH + "/#", INSTRUMENT_ID);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new StoresDBHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case STORE:
                queryBuilder.setTables(StoresContract.StoreEntry.TABLE_NAME);
                break;
            case STORE_ID:
                queryBuilder.setTables(StoresContract.StoreEntry.TABLE_NAME);
                queryBuilder.appendWhere(StoresContract.StoreEntry.COLUMN_NAME_ENTRY_ID + "="
                        + uri.getLastPathSegment());
                break;
            case INSTRUMENT:
                queryBuilder.setTables(StoresContract.InstrumentEntry.TABLE_NAME);
                break;
            case INSTRUMENT_ID:
                queryBuilder.setTables(StoresContract.InstrumentEntry.TABLE_NAME);
                queryBuilder.appendWhere(StoresContract.InstrumentEntry.COLUMN_NAME_ENTRY_ID + "="
                        + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = dbHelper.getWritableDatabase();
        int rowsDeleted = 0;
        long id = 0;
        switch (uriType) {
            case STORE:
                id = sqlDB.insert(StoresContract.StoreEntry.TABLE_NAME, null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return Uri.parse(STORES_PATH + "/" + id);
            case INSTRUMENT:
                id = sqlDB.insert(StoresContract.InstrumentEntry.TABLE_NAME, null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return Uri.parse(INSTRUMENT_PATH + "/" + id);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }

}
