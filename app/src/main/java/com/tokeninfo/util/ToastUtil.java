package com.tokeninfo.util;

import android.app.Activity;
import android.widget.Toast;

public class ToastUtil {

    public static void show(Activity activity, String value) {
        Toast.makeText(activity, value, Toast.LENGTH_SHORT).show();
    }
}
