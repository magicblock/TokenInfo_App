package com.tokeninfo.ui.contract;

import android.app.Activity;
import com.tokeninfo.base.BasePresenter;
import com.tokeninfo.base.BaseResult;
import com.tokeninfo.base.BaseView;
import com.tokeninfo.ui.bean.AccountBeean;
import com.tokeninfo.ui.bean.ChannelBean;
import com.tokeninfo.ui.bean.RecordBean;

import java.util.List;

public interface MarginContract {

    interface BsView extends BaseView<MarginContract.Presenter> {

    }

    interface Presenter extends BasePresenter {

        void account(Activity activity, BaseResult<List<AccountBeean>> result);

        void channel(Activity activity, BaseResult<List<ChannelBean>> result);

        void records(Activity activity,int id,BaseResult<List<RecordBean>> result);
    }
}
