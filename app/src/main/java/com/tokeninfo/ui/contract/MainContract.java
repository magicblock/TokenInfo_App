package com.tokeninfo.ui.contract;

import com.tokeninfo.base.BasePresenter;
import com.tokeninfo.base.BaseResult;
import com.tokeninfo.base.BaseView;
import com.tokeninfo.ui.bean.RecordBean;

import java.util.List;

public interface MainContract {

    interface BsView extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {

        void deviceToken(String token);

        void account(BaseResult<String> result);

        void records(BaseResult<List<RecordBean>> result);
    }
}
