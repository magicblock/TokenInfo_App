package com.tokeninfo.util.share;

import android.text.TextUtils;
import com.tokeninfo.util.share.bean.BaseInfo;

public class AppInfo {

    private static String SERVER = "SERVER";//服务端地址
    private static String PUSH_TOKEN = "PUSH_TOKEN";//华为推送Token

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

    public void setServer(String server) {
        baseInfo.putValue(SERVER, server);
    }

    public String getServer() {
        String server = baseInfo.getStringValue(SERVER);
        return server;
    }

    public String getCompleteServer() {
        String server = baseInfo.getStringValue(SERVER);
        if (TextUtils.isEmpty(server)) {
            server = "47.96.148.26";
        }

        server = "http://" + server + ":9000";
        return server;
    }

    public void setPushToken(String token) {
        baseInfo.putValue(PUSH_TOKEN, token);
    }

    public String getPushToken() {
        return baseInfo.getStringValue(PUSH_TOKEN);
    }
}
