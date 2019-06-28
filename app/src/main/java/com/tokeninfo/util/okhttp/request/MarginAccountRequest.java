package com.tokeninfo.util.okhttp.request;

public class MarginAccountRequest extends GetRequest {

    @Override
    public String requstUri() {
        return "/v1/margin/account";
    }
}
