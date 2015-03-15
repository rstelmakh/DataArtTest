package com.rstelmakh.dataarttest.db.dao;

import android.content.ContentValues;
import android.net.Uri;

import com.rstelmakh.dataarttest.db.StoresContract;
import com.rstelmakh.dataarttest.db.StoresProvider;
import com.rstelmakh.dataarttest.model.Instrument;
import com.rstelmakh.dataarttest.model.Store;

/**
 * Created by roman on 3/15/2015.
 */
public class InstrumentDAO extends AbstractDao<Instrument>{

    @Override
    protected ContentValues createValues(Instrument entry) {
        ContentValues values = new ContentValues();
        values.put(StoresContract.InstrumentEntry.COLUMN_NAME_ENTRY_ID, entry.getId());
        values.put(StoresContract.InstrumentEntry.COLUMN_NAME_BRAND, entry.getBrand());
        values.put(StoresContract.InstrumentEntry.COLUMN_NAME_MODEL, entry.getModel());
        values.put(StoresContract.InstrumentEntry.COLUMN_NAME_PRICE, entry.getPrice());
        values.put(StoresContract.InstrumentEntry.COLUMN_NAME_QUANTITY, entry.getQuantity());
        values.put(StoresContract.InstrumentEntry.COLUMN_NAME_TYPE, entry.getType());
        values.put(StoresContract.InstrumentEntry.COLUMN_NAME_STORE_ID, entry.getStoreId());

        return values;
    }

    @Override
    protected Uri getUri() {
        return StoresProvider.INSTRUMENT_URI;
    }
}
