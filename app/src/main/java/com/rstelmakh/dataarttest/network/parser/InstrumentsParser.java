package com.rstelmakh.dataarttest.network.parser;

import com.rstelmakh.dataarttest.model.Instrument;

import org.json.JSONObject;


/**
 * Created by roman on 3/15/2015.
 */
public class InstrumentsParser extends BaseArrayParser<Instrument> {

    private int storeId;

    public InstrumentsParser(String data, int storeId){
        super(data);
        this.storeId = storeId;
    }

    @Override
    protected Instrument parseItem(JSONObject json) {
        Instrument instrument = new Instrument();

        JSONObject jsonInstrument = json.optJSONObject("instrument");
        if(jsonInstrument != null){
            instrument.setId(jsonInstrument.optInt("id"));
            instrument.setBrand(jsonInstrument.optString("brand"));
            instrument.setModel(jsonInstrument.optString("model"));
            instrument.setType(jsonInstrument.optString("type"));
            instrument.setPrice((float) jsonInstrument.optDouble("price"));
        }
        instrument.setQuantity(json.optInt("quantity"));
        instrument.setStoreId(storeId);

        return instrument;
    }
}