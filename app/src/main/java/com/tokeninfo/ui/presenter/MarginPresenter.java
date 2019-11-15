package com.tokeninfo.ui.presenter;

import android.app.Activity;
import com.tokeninfo.base.BaseResult;
import com.tokeninfo.ui.bean.AccountBeean;
import com.tokeninfo.ui.bean.ChannelBean;
import com.tokeninfo.ui.bean.RecordBean;
import com.tokeninfo.ui.contract.MarginContract;
import com.tokeninfo.util.okhttp.Callback.RequstBack;
import com.tokeninfo.util.okhttp.OKRequest;
import com.tokeninfo.util.okhttp.request.AccountRequest;
import com.tokeninfo.util.okhttp.request.ChannelRequest;
import com.tokeninfo.util.okhttp.request.RecordsRequest;

import java.util.List;

public class MarginPresenter implements MarginContract.Presenter {

    MarginContract.BsView baseview;

    public MarginPresenter(MarginContract.BsView baseview) {
        this.baseview = baseview;
        baseview.setPresenter(this);
    }

    @Override
    public void start() {
        baseview.init();
    }

    @Override
    public void account(Activity activity, BaseResult<List<AccountBeean>> result) {
        AccountRequest request = new AccountRequest();
        OKRequest.client().request(request, new RequstBack<List<AccountBeean>>(activity) {

            @Override
            public void success(List<AccountBeean> beans) {
                result.success(beans);
            }

            @Override
            public void serverError(int code, String err) {

            }

            @Override
            public void netError() {

            }
        });
    }

    @Override
    public void channel(Activity activity, BaseResult<List<ChannelBean>> result) {
        ChannelRequest request = new ChannelRequest();
        OKRequest.client().request(request, new RequstBack<List<ChannelBean>>(activity) {

            @Override
            public void success(List<ChannelBean> beans) {
                result.success(beans);
            }

            @Override
            public void serverError(int code, String err) {

            }

            @Override
            public void netError() {

            }
        });
    }

    @Override
    public void records(Activity activity, int id, BaseResult<List<RecordBean>> result) {
        RecordsRequest request = new RecordsRequest(id);
        OKRequest.client().request(request, new RequstBack<List<RecordBean>>(activity) {

            @Override
            public void success(List<RecordBean> recordBeans) {
                result.success(recordBeans);
            }

            @Override
            public void serverError(int code, String err) {

            }

            @Override
            public void netError() {

            }
        });
    }
}
