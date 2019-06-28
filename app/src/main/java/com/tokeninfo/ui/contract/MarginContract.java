package com.tokeninfo.ui.contract;

import android.app.Activity;
import com.tokeninfo.base.BasePresenter;
import com.tokeninfo.base.BaseResult;
import com.tokeninfo.base.BaseView;
import com.tokeninfo.ui.bean.RecordBean;

import java.util.List;

public interface MarginContract {

    interface BsView extends BaseView<MarginContract.Presenter> {

        void pull();
    }

    interface Presenter extends BasePresenter {

        void account(Activity activity, BaseResult<String> result);

        void records(Activity activity,int id,BaseResult<List<RecordBean>> result);
    }
}
