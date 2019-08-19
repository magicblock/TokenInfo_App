package com.tokeninfo.ui.presenter;

import android.app.Activity;
import com.tokeninfo.ui.contract.MainContract;
import com.tokeninfo.util.ToastUtil;
import com.tokeninfo.util.okhttp.Callback.RequstBack;
import com.tokeninfo.util.okhttp.OKRequest;
import com.tokeninfo.util.okhttp.request.HwTokenRequest;

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
}