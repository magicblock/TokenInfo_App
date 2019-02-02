package com.tokeninfo.util.okhttp.request;

import com.tokeninfo.util.ApiUtil;
import com.tokeninfo.util.okhttp.bean.HttpMethodEnum;

public class UploadTokenRequest extends BaseRequest {

    private String value;

    public UploadTokenRequest(String value) {
        super(value);
        this.value = value;
    }

    @Override
    public String requstUri() {
        return ApiUtil.UPLOAD_TOKEN + "?" + value;
    }

    @Override
    public HttpMethodEnum httpMethod() {
        return HttpMethodEnum.GET;
    }
}
