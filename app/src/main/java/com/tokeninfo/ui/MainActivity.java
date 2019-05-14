package com.tokeninfo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.PushCallback;
import com.PushSDK;
import com.tokeninfo.R;
import com.tokeninfo.base.BaseActivity;
import com.tokeninfo.ui.contract.MainContract;
import com.tokeninfo.ui.presenter.MainPresenter;
import com.tokeninfo.util.SystermUtil;
import com.tokeninfo.util.ToastUtil;
import com.tokeninfo.util.share.AppInfo;
import com.tokeninfo.widget.ToolBar;

public class MainActivity extends BaseActivity implements MainContract.BsView {

    @BindView(R.id.toolbar)
    ToolBar toolbar;
    @BindView(R.id.txt_notice)
    TextView txtNotice;
    @BindView(R.id.txt_token)
    TextView txtToken;

    private MainActivity activity;
    private MainContract.Presenter presenter;

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
        toolbar.setTitle("Home");

        PushSDK.getPushSDK().connnect(activity, new PushCallback() {

            @Override
            public void token(final String token) {
                AppInfo.getAppInfo().setPushToken(token);

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtToken.setText(token);
                    }
                });
            }
        });
    }

    @OnClick(R.id.txt_notice)
    void Onclick() {
        String string = txtToken.getText().toString();
        if (!TextUtils.isEmpty(string)) {
            SystermUtil.Copy(activity, string);
            ToastUtil.show(activity,"复制成功");
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
