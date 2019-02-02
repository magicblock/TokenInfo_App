package com;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.huawei.android.hms.agent.HMSAgent;
import com.huawei.android.hms.agent.common.handler.ConnectHandler;
import com.huawei.android.hms.agent.push.handler.GetTokenHandler;

public class PushSDK {

    private static String TAG = "_PushSDK";
    private static PushSDK pushSDK;

    public synchronized static PushSDK getPushSDK() {
        if (pushSDK == null) {
            pushSDK = new PushSDK();
        }
        return pushSDK;
    }

    private PushCallback pushCallback;

    public void init(Application context) {
        HMSAgent.init(context);
    }

    public void connnect(Activity activity,PushCallback callback){
        this.pushCallback = callback;

        HMSAgent.connect(activity, new ConnectHandler() {
            @Override
            public void onConnect(int rst) {
                Log.d(TAG, "HMS connect: " + rst);
            }
        });
        HMSAgent.Push.getToken(new GetTokenHandler() {

            @Override
            public void onResult(int rst) {
                Log.d(TAG, "HMS onResult: " + rst);
            }
        });
    }

    public PushCallback getPushCallback(){
        return pushCallback;
    }
}
