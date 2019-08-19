package com.tokeninfo.util.okhttp.request;

import com.tokeninfo.util.share.AppInfo;
import okhttp3.Request;

import java.util.Iterator;
import java.util.Map;

public abstract class GetRequest extends BaseRequest {

    @Override
    public Request request() {
        String url = AppInfo.getAppInfo().getCompleteServer() + requstUri();
        Request.Builder requestBuilder = new Request.Builder().url(url);

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
