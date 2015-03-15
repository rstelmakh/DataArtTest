package com.rstelmakh.dataarttest;

import android.app.Activity;
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

import com.rstelmakh.dataarttest.db.StoresProvider;
import com.rstelmakh.dataarttest.network.NetworkClient;

/**
 * Created by roman on 3/15/2015.
 */
public class StoresListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int URL_LOADER = 0;

    private OnStoreSelectedListener onStoreSelectedListener;
    private CursorAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getLoaderManager().initLoader(URL_LOADER, null, this);

        new NetworkClient().loadStoresList(getActivity());
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(getActivity(), StoresProvider.STORES_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        if(adapter == null){
            adapter = new StoresAdapter(getActivity(), cursor, true);
            ListView listView = (ListView) getView().findViewById(R.id.listView);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    onStoreSelectedListener.onStoreSelected(id);
                }
            });
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


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        onStoreSelectedListener = (OnStoreSelectedListener) activity;
    }
}
