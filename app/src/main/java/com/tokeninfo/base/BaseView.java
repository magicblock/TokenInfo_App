package com.tokeninfo.base;

import android.app.Activity;

public interface BaseView<T> {

    void init();

    void setPresenter(T presenter);

    Activity bsView();
}