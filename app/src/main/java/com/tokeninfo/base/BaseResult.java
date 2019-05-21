package com.tokeninfo.base;

public interface BaseResult<T> {

    void success(T t);

    void fail(String string);
}
