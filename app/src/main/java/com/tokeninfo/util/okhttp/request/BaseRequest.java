package com.tokeninfo.util.okhttp.request;

import com.google.gson.Gson;
import com.tokeninfo.util.ApiUtil;
import com.tokeninfo.util.log.LogUtil;
import com.tokeninfo.util.okhttp.bean.HttpMethodEnum;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public abstract class BaseRequest<T> {

    private static final String TAG = "_BaseRequest";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private Object object;
    private Map<String, String> stringMap;

    public BaseRequest(Object obj) {
        this.object = obj;
        stringMap = new HashMap<>();
    }

    public abstract String requstUri();

    public abstract HttpMethodEnum httpMethod();

    public Object requestBody() {
        return object;
    }

    public void putHeaders(String key, String value) {
        stringMap.put(key, value);
    }

    public Map<String, String> getStringMap() {
        return stringMap;
    }

    public Request request() {
        Request.Builder requestBuilder = null;
        if (httpMethod() == HttpMethodEnum.GET) {
            String url = ApiUtil.SERVER + requstUri();
            LogUtil.d(TAG, "url: " + url);

            requestBuilder = new Request.Builder()
                    .url(url);

            Map<String, String> stringMap = getStringMap();
            Iterator<Map.Entry<String, String>> entries = stringMap.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                String key = entry.getKey();
                String value = entry.getValue();
                requestBuilder.addHeader(key, value);
            }
        } else if (httpMethod() == HttpMethodEnum.POST) {
            String url = ApiUtil.SERVER + requstUri();

            String json = new Gson().toJson(requestBody());
            RequestBody body = RequestBody.create(JSON, json);
            requestBuilder = new Request.Builder()
                    .url(url)
                    .post(body);

            Map<String, String> stringMap = getStringMap();
            Iterator<Map.Entry<String, String>> entries = stringMap.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                String key = entry.getKey();
                String value = entry.getValue();
                requestBuilder.addHeader(key, value);
            }
        }
        return requestBuilder.build();
    }
}
