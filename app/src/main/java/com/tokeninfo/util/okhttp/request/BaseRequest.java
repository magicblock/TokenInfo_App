package com.tokeninfo.util.okhttp.request;

import okhttp3.Request;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseRequest {

    private static final String TAG = "_BaseRequest";
    private Object object;
    private Map<String, String> stringMap = new HashMap<>();

    public abstract String requstUri();

    public abstract Request request();

    public Object requestBody() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public void putHeaders(String key, String value) {
        stringMap.put(key, value);
    }

    public Map<String, String> getStringMap() {
        return stringMap;
    }
}
