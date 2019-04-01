package com.tokeninfo.ui.presenter;

import android.app.Activity;
import com.tokeninfo.ui.bean.TargetBean;
import com.tokeninfo.ui.contract.MainContract;
import com.tokeninfo.util.okhttp.Callback.BaseCallBack;
import com.tokeninfo.util.okhttp.OKHttpUtil;
import com.tokeninfo.util.okhttp.request.RemoveRequest;
import com.tokeninfo.util.okhttp.request.TargetListRequest;
import com.tokeninfo.util.okhttp.request.UploadTargetRequest;

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
        activity = bsView.bsView();
    }

    @Override
    public void uploadTarget(String plat,String symbol,String price) {
        UploadTargetRequest request = new UploadTargetRequest(plat,symbol,price);
        OKHttpUtil.client().request(request, new BaseCallBack<String>(activity) {
            @Override
            public void success(String string) {
                refreshList();
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
    public void refreshList() {
        TargetListRequest tokenRequest = new TargetListRequest();
        OKHttpUtil.client().request(tokenRequest, new BaseCallBack<List<TargetBean>>(activity) {
            @Override
            public void success(List<TargetBean> targetBeans) {
                bsView.showTargets(targetBeans);
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
    public void remove(String plat,String symbol,String price) {
        RemoveRequest request = new RemoveRequest(plat,symbol,price);
        OKHttpUtil.client().request(request, new BaseCallBack<String>(activity) {
            @Override
            public void success(String string) {
                refreshList();
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
