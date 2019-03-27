package com.tokeninfo.ui.contract;

import com.tokeninfo.base.BasePresenter;
import com.tokeninfo.base.BaseView;
import com.tokeninfo.ui.bean.TargetBean;

import java.util.List;

public interface MainContract {

    interface BsView extends BaseView<Presenter> {

        void showTargets(List<TargetBean> targetBeans);
    }

    interface Presenter extends BasePresenter {

        void uploadPushToken(String userToken);

        void uploadTarget(String symbol,String price);

        void refreshList();

        void remove(String symbol,String pricce);
    }
}
