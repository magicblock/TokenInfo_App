package com.tokeninfo.ui.contract;

import com.tokeninfo.base.BasePresenter;
import com.tokeninfo.base.BaseView;

public interface HostContract {

    interface BsView extends BaseView<HostContract.Presenter> {
    }

    interface Presenter extends BasePresenter {
    }
}
