package com.tokeninfo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.PushCallback;
import com.PushSDK;
import com.tokeninfo.R;
import com.tokeninfo.base.BaseActivity;
import com.tokeninfo.ui.bean.TargetBean;
import com.tokeninfo.ui.contract.MainContract;
import com.tokeninfo.ui.presenter.MainPresenter;
import com.tokeninfo.util.ApiUtil;
import com.tokeninfo.util.share.AppInfo;

import java.util.List;

public class MainActivity extends BaseActivity implements MainContract.BsView {

    @BindView(R.id.btn_refresh)
    Button btnRefresh;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.edit_symbol)
    EditText editSymbol;
    @BindView(R.id.edit_price)
    EditText editPrice;
    @BindView(R.id.btn_upload)
    Button btnUpload;
    @BindView(R.id.edit_server)
    EditText editServer;
    @BindView(R.id.btn_server)
    Button btnServer;

    private MainActivity activity;
    private MainContract.Presenter presenter;

    private TargetAdapter targetAdapter;

    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 120:
                    String token = (String) msg.obj;
                    presenter.uploadPushToken(token);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        new MainPresenter(this).start();
    }

    @Override
    public void init() {
        activity = this;

        ApiUtil.SERVER = "http:" + editServer.getText().toString() + ":8099";
        PushSDK.getPushSDK().connnect(activity, new PushCallback() {

            @Override
            public void token(String token) {
                AppInfo.getAppInfo().setPushToken(token);

                Message message = new Message();
                message.what = 120;
                message.obj = token;
                handler.sendMessage(message);
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(activity);
        recyclerview.setLayoutManager(manager);
        targetAdapter = new TargetAdapter();
        recyclerview.setAdapter(targetAdapter);
        targetAdapter.setCallBack(new TargetAdapter.CallBack() {

            @Override
            public void remove(TargetBean bean) {
                presenter.remove(bean.getSymbol(), String.valueOf(bean.getPrice()));
            }
        });
    }

    @Override
    public void showTargets(List<TargetBean> targetBeans) {
        targetAdapter.setBeanList(targetBeans);
    }

    @OnClick({R.id.btn_server, R.id.btn_refresh, R.id.btn_upload})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_server:
                ApiUtil.SERVER = "http:" + editServer.getText().toString() + ":8099";
                String token = AppInfo.getAppInfo().getPushToken();
                presenter.uploadPushToken(token);
                break;
            case R.id.btn_upload:
                String symbol = editSymbol.getText().toString();
                String price = editPrice.getText().toString();
                presenter.uploadTarget(symbol, price);
                break;
            case R.id.btn_refresh:
                presenter.refreshList();
                break;
        }
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Activity bsView() {
        return this;
    }
}
