package com.tokeninfo.ui.contract;

import com.tokeninfo.base.BasePresenter;
import com.tokeninfo.base.BaseView;

public interface MainContract {

    interface BsView extends BaseView<Presenter> {

        void fragment(int position);
    }

    interface Presenter extends BasePresenter {

        void deviceToken(String token);
    }
}
