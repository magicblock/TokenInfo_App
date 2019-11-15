package com.tokeninfo.util.okhttp.request;

public class ChannelRequest extends GetRequest {

    @Override
    public String requstUri() {
        return "/v1/margin/channel";
    }
}
