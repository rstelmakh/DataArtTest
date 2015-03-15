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
public class InstrumentsAdapter extends CursorAdapter {

    public InstrumentsAdapter(Context context, Cursor c, boolean autoRequery) {
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
        return LayoutInflater.from(context).inflate(R.layout.instrument_list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewBrand = (TextView) view.findViewById(R.id.brand_model);
        String brand = cursor.getString( cursor.getColumnIndex(StoresContract.InstrumentEntry.COLUMN_NAME_BRAND));
        String model = cursor.getString( cursor.getColumnIndex(StoresContract.InstrumentEntry.COLUMN_NAME_MODEL));
        textViewBrand.setText(brand + " - " + model);

        TextView textType = (TextView) view.findViewById(R.id.type);
        String type = cursor.getString( cursor.getColumnIndex(StoresContract.InstrumentEntry.COLUMN_NAME_TYPE));
        textType.setText(type);

        TextView textPrice = (TextView) view.findViewById(R.id.price);
        String price = cursor.getString( cursor.getColumnIndex(StoresContract.InstrumentEntry.COLUMN_NAME_PRICE));
        textPrice.setText("Price: " + price);

        TextView textQuantity = (TextView) view.findViewById(R.id.quantity);
        String quantity = cursor.getString( cursor.getColumnIndex(StoresContract.InstrumentEntry.COLUMN_NAME_QUANTITY));
        textQuantity.setText("Quantity: " + quantity);
    }
}
