package com.tokeninfo.util.okhttp.request;

import com.tokeninfo.util.ApiUtil;
import com.tokeninfo.util.okhttp.bean.HttpMethodEnum;

public class BiCoinRequest extends BaseRequest {

    private String value;

    public BiCoinRequest(String value) {
        super(value);
        this.value = value;
    }

    @Override
    public String requstUri() {
        return ApiUtil.APP + "?" + value;
    }

    @Override
    public HttpMethodEnum httpMethod() {
        return HttpMethodEnum.GET;
    }
}
