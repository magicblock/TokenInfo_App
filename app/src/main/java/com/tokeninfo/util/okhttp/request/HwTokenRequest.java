package com.tokeninfo.util.okhttp.request;

import com.tokeninfo.util.DeviceUtil;

public class HwTokenRequest extends GetRequest {

    private String value;

    public HwTokenRequest(String value) {
        this.value = value;
    }

    @Override
    public String requstUri() {
        String name = DeviceUtil.deviceName();
        return "/v1/device" + "?name=" + name + "&token=" + value;
    }
}
