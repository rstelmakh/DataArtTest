package com.rstelmakh.dataarttest.network.parser;

import com.rstelmakh.dataarttest.model.Instrument;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roman on 3/15/2015.
 */
public abstract class BaseArrayParser<T> {

    private String data;

    public BaseArrayParser(String data){
        this.data = data;
    }

    public List<T> parse() throws JSONException {
        List<T> result = new ArrayList<>();

        JSONArray array = new JSONArray(data);
        int size = array.length();
        for (int i = 0; i < size; i++) {
            JSONObject jsonStore = array.optJSONObject(i);
            if (jsonStore != null) {
                T store = parseItem(jsonStore);
                result.add(store);
            }
        }

        return result;
    }

    protected abstract T parseItem(JSONObject json);
}
