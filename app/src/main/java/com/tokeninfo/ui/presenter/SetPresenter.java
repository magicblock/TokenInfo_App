package com.tokeninfo.ui.presenter;

import android.app.Activity;
import android.widget.Toast;
import com.tokeninfo.ui.contract.SetContract;
import com.tokeninfo.util.DeviceUtil;
import com.tokeninfo.util.okhttp.Callback.BaseCallBack;
import com.tokeninfo.util.okhttp.OKHttpUtil;
import com.tokeninfo.util.okhttp.request.UploadTokenRequest;
import com.tokeninfo.util.share.AppInfo;

public class SetPresenter implements SetContract.Presenter {

    private Activity activity;
    private SetContract.BsView bsView;

    public SetPresenter(SetContract.BsView bsView) {
        this.bsView = bsView;
        this.bsView.setPresenter(this);
    }

    @Override
    public void start() {
        bsView.init();
        activity = bsView.bsView();
    }

    @Override
    public void uploadPushToken() {
        String device = DeviceUtil.deviceName();
        String pushToken = AppInfo.getAppInfo().getPushToken();

        String value = "device=" + device + "&token=" + pushToken;
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
}
