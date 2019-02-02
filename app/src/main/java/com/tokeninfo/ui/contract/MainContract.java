package com.tokeninfo.ui.contract;

import com.tokeninfo.base.BasePresenter;
import com.tokeninfo.base.BaseView;
import com.tokeninfo.ui.bean.NotificationBean;

public interface MainContract {

    interface BsView extends BaseView<Presenter> {

        void enableNotifcation();

    }

    interface Presenter extends BasePresenter {

        void clearPushToken(String userToken);

        void uploadPushToken(String userToken);

        void uploadNotification(NotificationBean notificationBean);
    }
}
