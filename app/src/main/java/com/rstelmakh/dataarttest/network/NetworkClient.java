package com.rstelmakh.dataarttest.network;

import android.content.Context;
import android.content.Intent;

/**
 * Created by roman on 3/15/2015.
 */
public class NetworkClient {
    public static final String REQUEST = "request";

    public void loadStoresList(Context context){
        Intent serviceIntent = new Intent(context, NetworkService.class);
        serviceIntent.putExtra(REQUEST, Request.STORES);
        context.startService(serviceIntent);
    }


    public void loadStore(Context context, int storeId){
        Intent serviceIntent = new Intent(context, NetworkService.class);

        String url = String.format(Request.STORE_DETAILS.getUrl(), storeId);
        Request request = Request.STORE_DETAILS;
        request.setUrl(url);
        serviceIntent.putExtra(REQUEST, request);
        context.startService(serviceIntent);
    }
}
