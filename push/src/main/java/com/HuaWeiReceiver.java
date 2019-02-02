package com;

import android.content.Context;
import android.util.Log;

import com.huawei.hms.support.api.push.PushReceiver;

public class HuaWeiReceiver extends PushReceiver {

    private static String TAG = "_HuaWeiReceiver";

    @Override
    public void onToken(Context context, String token) {
        super.onToken(context, token);
        Log.d(TAG, token);
        PushSDK.getPushSDK().getPushCallback().token(token);
    }
}
