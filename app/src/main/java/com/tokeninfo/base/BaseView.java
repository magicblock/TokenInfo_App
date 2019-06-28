package com.tokeninfo.base;

public interface BaseView<T> {

    void init();

    void setPresenter(T presenter);
}
