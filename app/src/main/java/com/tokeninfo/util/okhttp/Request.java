package com.tokeninfo.util.okhttp;

public class Request {

    private static Request request;

    public synchronized static Request request() {
        if (request == null) {
            request = new Request();
        }
        return request;
    }
}
