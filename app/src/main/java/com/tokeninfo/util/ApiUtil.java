package com.tokeninfo.util;

public class ApiUtil {

    /** 服务器地址 */
    public static String SERVER = "";
    public static String CLEAR_TOKEN = "/tokeninfo/v1/clear_token";
    public static String UPLOAD_TOKEN = "/tokeninfo/v1/upload_token";
    public static String APP = "/tokeninfo/v1/app";

    public static void setSERVER(String SERVER) {
        ApiUtil.SERVER = SERVER;
    }
}

