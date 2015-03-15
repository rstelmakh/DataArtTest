package com.rstelmakh.dataarttest.network;

import android.app.IntentService;
import android.content.Intent;

import com.rstelmakh.dataarttest.HttpHelper;
import com.rstelmakh.dataarttest.db.StoresDBHelper;
import com.rstelmakh.dataarttest.db.dao.Dao;
import com.rstelmakh.dataarttest.db.dao.DaoManager;
import com.rstelmakh.dataarttest.db.dao.InstrumentDAO;
import com.rstelmakh.dataarttest.db.dao.StoreDAO;
import com.rstelmakh.dataarttest.network.parser.BaseArrayParser;
import com.rstelmakh.dataarttest.network.parser.InstrumentsParser;
import com.rstelmakh.dataarttest.network.parser.StoresParser;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import java.util.List;

/**
 * Created by roman on 3/15/2015.
 */
public class NetworkService extends IntentService {

    public NetworkService() {
        super(NetworkService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Request request = (Request) intent.getSerializableExtra(NetworkClient.REQUEST);
        loadData(request);
    }

    private void loadData(Request requestType){
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(requestType.getUrl());
        try{
            HttpResponse response = client.execute(request);
            String responseString = HttpHelper.request(response);

            processResponse(responseString, requestType);

        }catch(Exception ex){
            onLoadError(ex);
            ex.printStackTrace();
        }
    }

    private void processResponse(String jsonData, Request requestType){
        Dao storeDao = null;
        BaseArrayParser parser = null;

        switch (requestType){
            case STORES:
                storeDao = DaoManager.createDao(StoreDAO.class);
                parser = new StoresParser(jsonData);
                new StoresDBHelper(getBaseContext()).clearStores();
                break;
            case STORE_DETAILS:
                storeDao = DaoManager.createDao(InstrumentDAO.class);
                parser = new InstrumentsParser(jsonData, getStoreId(requestType.getUrl()));
                break;
        }

        try {
            List data = parser.parse();
            onLoadSuccess(data, storeDao);
        } catch (JSONException e) {
            onLoadError(e);
            e.printStackTrace();
        }

    }

    private void onLoadSuccess(List data, Dao dao){
        for(Object entry : data){
            dao.save(entry, getContentResolver());
        }
    }

    private int getStoreId(String url){
        String urlLast = url.substring(0, url.lastIndexOf("/"));
        String storeId = urlLast.substring(url.lastIndexOf("/") - 1);

        return Integer.parseInt(storeId);
    }

    private void onLoadError(Exception e){
//        TODO report error here
    }
}
