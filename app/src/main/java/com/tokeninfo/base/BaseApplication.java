package com.tokeninfo.base;

import android.app.Application;

import com.PushSDK;
import com.alibaba.android.arouter.launcher.ARouter;

public class BaseApplication extends Application {

    private static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        init();
    }

    protected void init() {
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);

        PushSDK.getPushSDK().init(this);
    }

    public static BaseApplication getBaseApplication() {
        return baseApplication;
    }
}
