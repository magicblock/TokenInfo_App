package com.tokeninfo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.tokeninfo.R;
import com.tokeninfo.base.BaseActivity;
import com.tokeninfo.ui.contract.SetContract;
import com.tokeninfo.ui.presenter.SetPresenter;
import com.tokeninfo.util.share.AppInfo;
import com.tokeninfo.widget.ToolBar;

public class SetActivity extends BaseActivity implements SetContract.BsView {

    @BindView(R.id.edit_server)
    EditText editServer;
    @BindView(R.id.btn_server)
    Button btnServer;
    @BindView(R.id.toolbar)
    ToolBar toolbar;

    private SetActivity activity;
    private SetContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);

        new SetPresenter(this).start();
    }

    @Override
    public void init() {
        activity = this;

        toolbar.setTitle("返回");
        toolbar.setTitleListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });

        String server = AppInfo.getAppInfo().getServer();
        editServer.setText(server);
    }

    @OnClick({R.id.btn_server})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_server:
                String server = editServer.getText().toString();
                AppInfo.getAppInfo().setServer(server);

                presenter.uploadPushToken();
                break;
        }
    }

    @Override
    public void setPresenter(SetContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Activity bsView() {
        return activity;
    }
}
