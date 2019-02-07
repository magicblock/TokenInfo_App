package com.tokeninfo.ui.presenter;

import android.app.Activity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tokeninfo.ui.bean.NotificationBean;
import com.tokeninfo.ui.contract.MainContract;
import com.tokeninfo.util.DeviceUtil;
import com.tokeninfo.util.okhttp.Callback.BaseCallBack;
import com.tokeninfo.util.okhttp.OKHttpUtil;
import com.tokeninfo.util.okhttp.request.BiCoinRequest;
import com.tokeninfo.util.okhttp.request.ClearTokenRequest;
import com.tokeninfo.util.okhttp.request.UploadTokenRequest;

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
        bsView.enableNotifcation();
    }

    @Override
    public void clearPushToken(String userToken) {
        String value = "token=" + userToken;
        ClearTokenRequest tokenRequest = new ClearTokenRequest(value);
        OKHttpUtil.client().request(tokenRequest, new BaseCallBack<String>(activity) {
            @Override
            public void success(String string) {
                Toast.makeText(activity, "success", Toast.LENGTH_SHORT).show();
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
    public void uploadPushToken(String userToken) {
        String device = DeviceUtil.deviceName();
        String value = "device=" + device + "&token=" + userToken;
        UploadTokenRequest tokenRequest = new UploadTokenRequest(value);
        OKHttpUtil.client().request(tokenRequest, new BaseCallBack<String>(activity) {
            @Override
            public void success(String string) {
                Toast.makeText(activity, "success", Toast.LENGTH_SHORT).show();
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
    public void uploadNotification(NotificationBean notification) {
        String value = "notification=" + new Gson().toJson(notification);
        BiCoinRequest coinRequest = new BiCoinRequest(value);
        OKHttpUtil.client().request(coinRequest, new BaseCallBack<String>(activity) {
            @Override
            public void success(String string) {
                Toast.makeText(activity, "success", Toast.LENGTH_SHORT).show();
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
