package com.tokeninfo.util.log;

import android.util.Log;

public class LogUtil {

    public static void i(String tag, String string) {
        Log.i(tag, string);
    }

    public static void d(String tag, String string) {
        Log.d(tag, string);
    }

    public static void e(String tag, String string) {
        Log.e(tag, string);
    }
}
