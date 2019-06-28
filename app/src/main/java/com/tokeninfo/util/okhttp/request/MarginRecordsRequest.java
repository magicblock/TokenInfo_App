package com.tokeninfo.util.okhttp.request;

public class MarginRecordsRequest extends GetRequest {

    private int id;

    public MarginRecordsRequest(int id) {
        this.id = id;
    }

    @Override
    public String requstUri() {
        return "/v1/margin/records?id=" + id;
    }
}
