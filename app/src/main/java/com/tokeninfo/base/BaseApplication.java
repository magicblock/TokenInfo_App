package com.tokeninfo.base;

import android.app.Application;

import android.content.Context;
import androidx.multidex.MultiDex;
import com.PushSDK;
import com.tencent.bugly.crashreport.CrashReport;

public class BaseApplication extends Application {

    private static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        init();
    }

    private void init() {
        CrashReport.initCrashReport(getApplicationContext(), "32a287ebde", false);
        PushSDK.getPushSDK().init(this);
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    public static BaseApplication getBaseApplication() {
        return baseApplication;
    }
}
