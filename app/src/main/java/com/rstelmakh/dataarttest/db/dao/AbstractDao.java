package com.rstelmakh.dataarttest.db.dao;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

/**
 * Created by roman on 3/15/2015.
 */
public abstract class AbstractDao<T> implements Dao<T> {

    @Override
    public void save(T entry, ContentResolver resolver){
        Uri newRowUri = resolver.insert(getUri(), createValues(entry));
    };

    protected abstract ContentValues createValues(T entry);

    protected abstract Uri getUri();
}
