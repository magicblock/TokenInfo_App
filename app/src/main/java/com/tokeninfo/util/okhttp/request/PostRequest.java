package com.tokeninfo.util.okhttp.request;

import com.google.gson.Gson;
import com.tokeninfo.util.share.AppInfo;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.Iterator;
import java.util.Map;

public abstract class PostRequest extends BaseRequest {

    @Override
    public Request request() {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        String url = AppInfo.getAppInfo().getCompleteServer() + requstUri();
        String json = new Gson().toJson(requestBody());
        RequestBody body = RequestBody.create(JSON, json);
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .post(body);

        Map<String, String> stringMap = getStringMap();
        if (stringMap != null && stringMap.size() > 0) {
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
