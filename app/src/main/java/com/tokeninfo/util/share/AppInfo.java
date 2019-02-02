package com.tokeninfo.util.share;

import com.tokeninfo.util.share.bean.BaseInfo;

public class AppInfo {

    private static String PUSH_TOKEN = "PUSH_TOKEN";

    private static AppInfo appInfo;
    private BaseInfo baseInfo;

    public AppInfo() {
        baseInfo = new BaseInfo("AppInfo");
    }

    public synchronized static AppInfo getAppInfo() {
        if (appInfo == null) {
            appInfo = new AppInfo();
        }
        return appInfo;
    }


    public void setPushToken(String token) {
        baseInfo.putValue(PUSH_TOKEN, token);
    }

    public String getPushToken() {
        return baseInfo.getStringValue(PUSH_TOKEN);
    }
}
