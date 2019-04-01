package com.tokeninfo.util.okhttp.request;

import com.tokeninfo.util.ApiUtil;
import com.tokeninfo.util.okhttp.bean.HttpMethodEnum;

public class UploadTargetRequest extends BaseRequest {

    private String plat;
    private String symbol;
    private String price;

    public UploadTargetRequest(String plat, String symbol, String price) {
        super("");
        this.plat = plat;
        this.symbol = symbol;
        this.price = price;
    }

    @Override
    public String requstUri() {
        return ApiUtil.TARGET + "?plat=" + plat + "&symbol=" + symbol + "&price=" + price;
    }

    @Override
    public HttpMethodEnum httpMethod() {
        return HttpMethodEnum.GET;
    }
}
