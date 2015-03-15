package com.rstelmakh.dataarttest.db.dao;

import android.content.ContentValues;
import android.net.Uri;

import com.rstelmakh.dataarttest.db.StoresContract;
import com.rstelmakh.dataarttest.db.StoresProvider;
import com.rstelmakh.dataarttest.model.Store;

/**
 * Created by roman on 3/15/2015.
 */
public class StoreDAO extends AbstractDao<Store>{

    @Override
    protected ContentValues createValues(Store entry) {
        ContentValues values = new ContentValues();
        values.put(StoresContract.StoreEntry.COLUMN_NAME_ENTRY_ID, entry.getId());
        values.put(StoresContract.StoreEntry.COLUMN_NAME_NAME, entry.getName());
        values.put(StoresContract.StoreEntry.COLUMN_NAME_ADDRESS, entry.getAddress());
        values.put(StoresContract.StoreEntry.COLUMN_NAME_PHONE, entry.getPhone());
        values.put(StoresContract.StoreEntry.COLUMN_NAME_LAT, entry.getLatitude());
        values.put(StoresContract.StoreEntry.COLUMN_NAME_LON, entry.getLongitude());

        return values;
    }

    @Override
    protected Uri getUri() {
        return StoresProvider.STORES_URI;
    }
}
