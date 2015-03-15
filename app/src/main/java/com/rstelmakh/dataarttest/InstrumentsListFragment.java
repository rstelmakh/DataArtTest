package com.rstelmakh.dataarttest;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.rstelmakh.dataarttest.db.StoresDBHelper;
import com.rstelmakh.dataarttest.db.StoresProvider;
import com.rstelmakh.dataarttest.network.NetworkClient;

/**
 * Created by roman on 3/15/2015.
 */
public class InstrumentsListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int URL_LOADER = 1;

    private long storeId;
    private CursorAdapter adapter;

    public static Fragment newInstance(long storeId){
        InstrumentsListFragment fragment = new InstrumentsListFragment();
        fragment.storeId = storeId;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new StoresDBHelper(getActivity()).clearInstruments();
        getLoaderManager().initLoader(URL_LOADER, null, this);

        new NetworkClient().loadStore(getActivity(), (int) storeId);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(getActivity(), StoresProvider.INSTRUMENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        if(adapter == null){
            adapter = new InstrumentsAdapter(getActivity(), cursor, true);
            ListView listView = (ListView) getView().findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }else{
            adapter.swapCursor(cursor);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        adapter.swapCursor(null);
    }

    private void openBrowser(String url){
        if(!TextUtils.isEmpty(url)){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }
    }
}
