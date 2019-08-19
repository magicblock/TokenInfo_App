package com.tokeninfo.util.okhttp.request;

public class SpotAccountRequest extends GetRequest {

    @Override
    public String requstUri() {
        return "/v1/spot/account";
    }
}
