package com.rstelmakh.dataarttest.db.dao;

import android.content.ContentResolver;

/**
 * Created by roman on 3/15/2015.
 */
public interface Dao<T> {
    public void save(T entry, ContentResolver resolver);
}
