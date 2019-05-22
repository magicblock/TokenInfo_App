package com.tokeninfo.util.okhttp;

import android.os.Handler;
import com.tokeninfo.util.okhttp.Callback.RequstBack;
import com.tokeninfo.util.okhttp.request.BaseRequest;
import okhttp3.Call;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class OKRequest {

    private static String TAg = "_OKHttpUtil";
    private static OKRequest httpUtil;
    private static OkHttpClient httpClient;

    private Handler handler;

    public synchronized static OKRequest client() {
        if (httpUtil == null) {
            httpUtil = new OKRequest();
        }
        return httpUtil;
    }

    public OKRequest() {
        handler = new Handler();
        httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    public void request(BaseRequest baseRequest, RequstBack requstBack) {
        requstBack.setHandler(handler);

        Call call = httpClient.newCall(baseRequest.request());
        call.enqueue(requstBack);
    }
}
