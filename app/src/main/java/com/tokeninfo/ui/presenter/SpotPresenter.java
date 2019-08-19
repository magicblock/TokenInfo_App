package com.tokeninfo.ui.presenter;

import android.app.Activity;
import com.tokeninfo.base.BaseResult;
import com.tokeninfo.ui.bean.AccountBeean;
import com.tokeninfo.ui.bean.RecordBean;
import com.tokeninfo.ui.contract.SpotContract;
import com.tokeninfo.util.okhttp.Callback.RequstBack;
import com.tokeninfo.util.okhttp.OKRequest;
import com.tokeninfo.util.okhttp.request.SpotAccountRequest;
import com.tokeninfo.util.okhttp.request.SpotRecordsRequest;

import java.util.List;

public class SpotPresenter implements SpotContract.Presenter {

    SpotContract.BsView baseview;

    public SpotPresenter(SpotContract.BsView baseview) {
        this.baseview = baseview;
        baseview.setPresenter(this);
    }

    @Override
    public void start() {
        baseview.init();
    }

    @Override
    public void account(Activity activity, BaseResult<String> result) {
        SpotAccountRequest request = new SpotAccountRequest();
        OKRequest.client().request(request, new RequstBack<List<AccountBeean>>(activity) {

            @Override
            public void success(List<AccountBeean> beans) {
                StringBuffer stringBuffer = new StringBuffer();
                for (AccountBeean bean : beans) {
                    stringBuffer.append(bean.getSymbol());
                    stringBuffer.append("[总额]" + bean.getTotal());

                    if (bean.getUsd() > 0) {
                        stringBuffer.append("  [USD]" + String.format("%6.2f", bean.getUsd()));
                    }
                    if (bean.getToken() > 0) {
                        stringBuffer.append("  [Token]" + String.format("%3.6f", bean.getToken()));
                    }

                    stringBuffer.append("\n");
                }

                result.success(stringBuffer.toString());
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
    public void records(Activity activity,int id, BaseResult<List<RecordBean>> result) {
        SpotRecordsRequest request = new SpotRecordsRequest(id);
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
