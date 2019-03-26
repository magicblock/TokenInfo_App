package com.tokeninfo.base;

import android.app.Application;

import com.PushSDK;

public class BaseApplication extends Application {

    private static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        init();
    }

    private void init() {
        PushSDK.getPushSDK().init(this);
    }

    public static BaseApplication getBaseApplication() {
        return baseApplication;
    }
}
