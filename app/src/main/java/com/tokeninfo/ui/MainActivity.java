package com.tokeninfo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.PushCallback;
import com.PushSDK;
import com.tokeninfo.R;
import com.tokeninfo.base.BaseActivity;
import com.tokeninfo.ui.bean.MessageEvent;
import com.tokeninfo.ui.contract.MainContract;
import com.tokeninfo.ui.presenter.MainPresenter;
import com.tokeninfo.util.ApiUtil;
import com.tokeninfo.util.share.AppInfo;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity implements MainContract.BsView {

    @BindView(R.id.txt_log)
    TextView txtLog;
    @BindView(R.id.edit_host)
    EditText editHost;
    @BindView(R.id.edit_port)
    EditText editPort;

    private MainActivity activity;
    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        new MainPresenter(this).start();
    }

    @Override
    public void init() {
        activity = this;
        txtLog.setText(getString(R.string.log_start));
        txtLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        String ip = editHost.getText().toString();
        String port = editPort.getText().toString();
        ApiUtil.setSERVER("http://" + ip + ":" + port);

        PushSDK.getPushSDK().connnect(activity, new PushCallback() {

            @Override
            public void token(String token) {
                MessageEvent.send(MessageEvent.MessageEnum.Notice, token);
                AppInfo.getAppInfo().setPushToken(token);

                presenter.uploadPushToken(token);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        MessageEvent.MessageEnum messageEnum = messageEvent.getMessageEnum();

        String showContent = "";
        switch (messageEnum) {
            case Notice:
                String token = (String) messageEvent.getObj();
                showContent = getString(R.string.log_push_token, token);
                break;
        }

        txtLog.append("\n");
        txtLog.append(showContent);
    }

    @OnClick({R.id.btn_send, R.id.txt_log})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                String token = AppInfo.getAppInfo().getPushToken();
                presenter.uploadPushToken(token);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
