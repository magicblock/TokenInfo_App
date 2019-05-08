package com.tokeninfo.util;

import java.util.regex.Pattern;

public class RegexpUtil {

    public static String Rule_Int = "^[0-9]{1,}$";
    public static String Rule_Float = "^([0-9]{1,}[.][0-9]*)$";

    public static boolean matches(String rule, String value) {
        return Pattern.matches(rule, value);
    }
}
