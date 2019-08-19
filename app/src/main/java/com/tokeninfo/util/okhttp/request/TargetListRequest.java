package com.tokeninfo.util.okhttp.request;

import com.tokeninfo.util.ApiUtil;

public class TargetListRequest extends GetRequest {

    @Override
    public String requstUri() {
        return ApiUtil.LIST;
    }
}
