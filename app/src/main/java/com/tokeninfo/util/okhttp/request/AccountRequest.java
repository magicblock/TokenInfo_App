package com.tokeninfo.util.okhttp.request;

public class AccountRequest extends GetRequest {

    @Override
    public String requstUri() {
        return "/v1/account";
    }
}
