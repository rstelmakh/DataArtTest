package com.rstelmakh.dataarttest.network.parser;

import com.rstelmakh.dataarttest.model.Store;

import org.json.JSONObject;


/**
 * Created by roman on 3/15/2015.
 */
public class StoresParser extends BaseArrayParser<Store> {

    public StoresParser(String data){
        super(data);
    }

    @Override
    protected Store parseItem(JSONObject json) {
        Store store = new Store();

        store.setId(json.optInt("id"));
        store.setName(json.optString("name"));
        store.setAddress(json.optString("address"));
        store.setPhone(json.optString("phone"));

        JSONObject jsonLocation = json.optJSONObject("location");
        if(jsonLocation != null){
            store.setLatitude(jsonLocation.optLong("latitude"));
            store.setLongitude(jsonLocation.optLong("longitude"));
        }

        return store;
    }
}