package com.tokeninfo.util.okhttp.request;

import com.tokeninfo.util.ApiUtil;

public class RemoveRequest extends GetRequest {

    private String plat;
    private String symbol;
    private String price;

    public RemoveRequest(String plat, String symbol, String price) {
        this.plat = plat;
        this.symbol = symbol;
        this.price = price;
    }

    @Override
    public String requstUri() {
        return ApiUtil.REMOVE + "?plat=" + plat + "&symbol=" + symbol + "&price=" + price;
    }
}
