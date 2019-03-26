package com.tokeninfo.ui.contract;

import com.tokeninfo.base.BasePresenter;
import com.tokeninfo.base.BaseView;

public interface MainContract {

    interface BsView extends BaseView<Presenter> {

        void enableNotifcation();

    }

    interface Presenter extends BasePresenter {
        void uploadPushToken(String userToken);
    }
}
