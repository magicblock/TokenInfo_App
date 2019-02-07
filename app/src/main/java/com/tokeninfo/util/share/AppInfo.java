package com.tokeninfo.util.share;

import com.tokeninfo.util.share.bean.BaseInfo;

public class AppInfo {

    private static String RECEIVE_PUSH = "RECEIVE_PUSH";//是否接受自己的推送
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

    public  void setReceivePush(boolean b) {
        baseInfo.putValue(RECEIVE_PUSH,b);
    }

    public  boolean getReceivePush() {
        return baseInfo.getBoolean(RECEIVE_PUSH);
    }

    public void setPushToken(String token) {
        baseInfo.putValue(PUSH_TOKEN, token);
    }

    public String getPushToken() {
        return baseInfo.getStringValue(PUSH_TOKEN);
    }
}
