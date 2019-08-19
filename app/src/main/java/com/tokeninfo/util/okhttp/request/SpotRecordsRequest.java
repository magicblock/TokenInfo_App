package com.tokeninfo.util.okhttp.request;

public class SpotRecordsRequest extends GetRequest {

    private int id;

    public SpotRecordsRequest(int id) {
        this.id = id;
    }

    @Override
    public String requstUri() {
        return "/v1/spot/records?id=" + id;
    }
}
