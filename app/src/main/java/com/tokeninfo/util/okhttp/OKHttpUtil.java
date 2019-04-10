package com.tokeninfo.util.okhttp;

import android.os.Handler;
import android.os.Looper;

import com.tokeninfo.util.okhttp.Callback.RequstBack;
import com.tokeninfo.util.okhttp.request.BaseRequest;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;

public class OKHttpUtil {

    private static String TAg = "_OKHttpUtil";
    private static OKHttpUtil httpUtil;
    private static OkHttpClient httpClient;

    private Handler handler;

    public synchronized static OKHttpUtil client() {
        if (httpUtil == null) {
            httpUtil = new OKHttpUtil();
        }
        return httpUtil;
    }

    public OKHttpUtil() {
        handler = new Handler(Looper.myLooper());
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
