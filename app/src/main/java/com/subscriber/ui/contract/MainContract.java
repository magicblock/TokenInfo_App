package com.subscriber.ui.contract;

import com.subscriber.base.BasePresenter;
import com.subscriber.base.BaseView;

public interface MainContract {

    interface BsView extends BaseView<MainContract.Presenter> {

    }

    interface Presenter extends BasePresenter {

    }
}
