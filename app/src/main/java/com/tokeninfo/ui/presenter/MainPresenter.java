package com.tokeninfo.ui.presenter;

import android.app.Activity;
<<<<<<< HEAD
import android.widget.Toast;
import com.tokeninfo.ui.bean.TargetBean;
import com.tokeninfo.ui.contract.MainContract;
import com.tokeninfo.util.DeviceUtil;
import com.tokeninfo.util.okhttp.Callback.RequstBack;
import com.tokeninfo.util.okhttp.OKHttpUtil;
import com.tokeninfo.util.okhttp.request.RemoveRequest;
import com.tokeninfo.util.okhttp.request.TargetListRequest;
import com.tokeninfo.util.okhttp.request.UploadTargetRequest;
import com.tokeninfo.util.okhttp.request.UploadTokenRequest;

import java.util.List;
=======
import com.tokeninfo.ui.contract.MainContract;
import com.tokeninfo.util.ToastUtil;
import com.tokeninfo.util.okhttp.Callback.RequstBack;
import com.tokeninfo.util.okhttp.OKRequest;
import com.tokeninfo.util.okhttp.request.HwTokenRequest;
>>>>>>> develop

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
<<<<<<< HEAD
    public void pushToken(String token) {
        String device = DeviceUtil.deviceName();

        String value = "device=" + device + "&token=" + token;
        UploadTokenRequest tokenRequest = new UploadTokenRequest(value);
        OKHttpUtil.client().request(tokenRequest, new RequstBack<String>(activity) {
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
    public void uploadTarget(String plat,String symbol,String price) {
        UploadTargetRequest request = new UploadTargetRequest(plat,symbol,price);
        OKHttpUtil.client().request(request, new RequstBack<String>(activity) {

            @Override
            public void success(String string) {
                refreshList();
            }
=======
    public void deviceToken(String token) {
        HwTokenRequest tokenRequest = new HwTokenRequest(token);
        OKRequest.client().request(tokenRequest, new RequstBack<String>(activity) {
>>>>>>> develop

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
