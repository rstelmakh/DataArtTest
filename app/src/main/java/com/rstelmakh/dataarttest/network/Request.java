package com.rstelmakh.dataarttest.network;

/**
 * Created by roman on 3/15/2015.
 */
public enum Request {
    STORES("http://aschoolapi.appspot.com/stores"),
    STORE_DETAILS("http://aschoolapi.appspot.com/stores/%s/instruments");

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    Request(String url){
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}