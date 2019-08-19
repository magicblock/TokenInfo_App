package com.tokeninfo.util;

import android.content.ClipboardManager;
import android.content.Context;

public class SystermUtil {

    public static void Copy(Context context, String string) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setText(string);
    }
}
