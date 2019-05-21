package com.tokeninfo.util.okhttp.request;

public class RecordsRequest extends GetRequest {

    @Override
    public String requstUri() {
        return "/v1/records";
    }
}
