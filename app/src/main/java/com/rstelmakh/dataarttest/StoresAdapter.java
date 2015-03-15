package com.rstelmakh.dataarttest;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.rstelmakh.dataarttest.db.StoresContract;

/**
 * Created by roman on 3/15/2015.
 */
public class StoresAdapter extends CursorAdapter {

    public StoresAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public long getItemId(int position) {
        Cursor cursor = getCursor();
        cursor.moveToPosition(position);
        return cursor.getLong(cursor.getColumnIndex(StoresContract.StoreEntry.COLUMN_NAME_ENTRY_ID));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.store_list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewName = (TextView) view.findViewById(R.id.name);
        String name = cursor.getString( cursor.getColumnIndex(StoresContract.StoreEntry.COLUMN_NAME_NAME));
        textViewName.setText(name);

        TextView textAddress = (TextView) view.findViewById(R.id.address);
        String address = cursor.getString( cursor.getColumnIndex(StoresContract.StoreEntry.COLUMN_NAME_ADDRESS));
        textAddress.setText(address);

        TextView textPhone = (TextView) view.findViewById(R.id.phone);
        String phone = cursor.getString( cursor.getColumnIndex(StoresContract.StoreEntry.COLUMN_NAME_PHONE));
        textPhone.setText(phone);
    }
}
