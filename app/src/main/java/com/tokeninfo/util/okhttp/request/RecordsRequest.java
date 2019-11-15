package com.tokeninfo.util.okhttp.request;

public class RecordsRequest extends GetRequest {

    private int id;

    public RecordsRequest(int id) {
        this.id = id;
    }

    @Override
    public String requstUri() {
        return "/v1/margin/records?id=" + id;
    }
}
