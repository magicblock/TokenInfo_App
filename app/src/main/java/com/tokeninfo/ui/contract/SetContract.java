package com.tokeninfo.ui.contract;

import com.tokeninfo.base.BasePresenter;
import com.tokeninfo.base.BaseView;

public interface SetContract {

    interface BsView extends BaseView<SetContract.Presenter> {

    }

    interface Presenter extends BasePresenter {

        void uploadPushToken();
    }
}
