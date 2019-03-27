package com.tokeninfo.util.okhttp.request;

import com.tokeninfo.util.ApiUtil;
import com.tokeninfo.util.okhttp.bean.HttpMethodEnum;

public class TargetListRequest extends BaseRequest {

    public TargetListRequest() {
        super("");
    }

    @Override
    public String requstUri() {
        return ApiUtil.LIST;
    }

    @Override
    public HttpMethodEnum httpMethod() {
        return HttpMethodEnum.GET;
    }
}
