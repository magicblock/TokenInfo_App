package com.tokeninfo.ui.presenter;

import android.app.Activity;
import com.tokeninfo.base.BaseResult;
import com.tokeninfo.ui.bean.AccountBeean;
import com.tokeninfo.ui.bean.RecordBean;
import com.tokeninfo.ui.contract.MainContract;
import com.tokeninfo.util.ToastUtil;
import com.tokeninfo.util.okhttp.Callback.RequstBack;
import com.tokeninfo.util.okhttp.OKRequest;
import com.tokeninfo.util.okhttp.request.AccountRequest;
import com.tokeninfo.util.okhttp.request.HwTokenRequest;
import com.tokeninfo.util.okhttp.request.RecordsRequest;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.BsView bsView;
    private Activity activity;

    public MainPresenter(MainContract.BsView bsView) {
        this.bsView = bsView;
        this.bsView.setPresenter(this);
    }

    @Override
    public void start() {
        bsView.init();
        activity = (Activity) bsView;
    }

    @Override
    public void deviceToken(String token) {
        HwTokenRequest tokenRequest = new HwTokenRequest(token);
        OKRequest.client().request(tokenRequest, new RequstBack<String>(activity) {

            @Override
            public void success(String o) {
                ToastUtil.show(activity, "token 上传成功");
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
    public void account(BaseResult<String> result) {
        AccountRequest request = new AccountRequest();
        OKRequest.client().request(request, new RequstBack<List<AccountBeean>>(activity) {

            @Override
            public void success(List<AccountBeean> beans) {
                StringBuffer stringBuffer = new StringBuffer();
                for (AccountBeean bean : beans) {
                    stringBuffer.append(bean.getSymbol());
                    stringBuffer.append("[总额]" + bean.getTotal());

                    if (bean.getUsd() > 0) {
                        stringBuffer.append("  [USD]" + String.format("%6.2f",bean.getUsd()));
                    }
                    if (bean.getToken() > 0) {
                        stringBuffer.append("  [Token]" + String.format("%3.6f",bean.getToken()));
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
    public void records(int id, BaseResult<List<RecordBean>> result) {
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
