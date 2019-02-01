package com.tokeninfo.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    protected void init() {
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
    }
}
