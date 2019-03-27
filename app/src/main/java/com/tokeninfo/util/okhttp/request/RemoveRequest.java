package com.tokeninfo.util.okhttp.request;

import com.tokeninfo.util.ApiUtil;
import com.tokeninfo.util.okhttp.bean.HttpMethodEnum;

public class RemoveRequest  extends BaseRequest {

    private String symbol;
    private String price;

    public RemoveRequest(String symbol, String price) {
        super("");
        this.symbol = symbol;
        this.price = price;
    }

    @Override
    public String requstUri() {
        return ApiUtil.REMOVE + "?symbol=" + symbol + "&price=" + price;
    }

    @Override
    public HttpMethodEnum httpMethod() {
        return HttpMethodEnum.GET;
    }
}
