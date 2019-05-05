package com.tokeninfo.util.okhttp.Callback;

import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.tokeninfo.R;
import com.tokeninfo.util.log.LogUtil;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class RequstBack<T> implements Callback {

    private static String TAG = "_BaseCallBack";

    private Activity activity;
    private Type beanType;
    private Handler handler;

    public RequstBack(Activity activity) {
        this.activity = activity;

        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        beanType = parameterizedType.getActualTypeArguments()[0];
    }

    @Override
    public void onResponse(Call call, Response response) {
        try {
            String body = response.body().string();
            LogUtil.d(TAG, body);
            if (200 == response.code()) {
                JsonParser parser = new JsonParser();
                JsonObject jsonObject = (JsonObject) parser.parse(body);
                JsonPrimitive codePrimitive = jsonObject.getAsJsonPrimitive("code");
                JsonPrimitive messagePrimitive = jsonObject.getAsJsonPrimitive("message");
                JsonElement dataElement = jsonObject.get("data");

                final int code = codePrimitive.getAsInt();
                if (code == 2000) {
                    String data = dataElement.toString();
                    final Object object = new Gson().fromJson(data, beanType);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            success((T) object);
                        }
                    });
                } else {
                    final String message = messagePrimitive.getAsString();

                    switch (code) {
                        case 2700:
                            toastNotity(message);
                            break;
                        default:
                            toastNotity(message);
                            break;
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            serverError(code, message);
                        }
                    });
                }
            } else {
                final int responseCode = response.code();
                final String errorMessage = response.message();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        serverError(responseCode, errorMessage);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            final String exceptionMessage = e.getMessage();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    serverError(404, exceptionMessage);
                }
            });
        }
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String string = activity.getString(R.string.error_network_timeout);
                if (e != null && TextUtils.isEmpty(e.getMessage())) {
                    string = e.getMessage();
                }

                netError();
            }
        }, 500);
    }

    public abstract void success(T t);

    public abstract void serverError(int code, String err);

    public abstract void netError();

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    private void toastNotity(final String message) {
        handler.post(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
