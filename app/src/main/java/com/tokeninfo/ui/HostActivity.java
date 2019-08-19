package com.tokeninfo.ui;

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
import com.tokeninfo.ui.contract.HostContract;
import com.tokeninfo.ui.presenter.HostPresenter;
import com.tokeninfo.util.share.AppInfo;
import com.tokeninfo.widget.ToolBar;

public class HostActivity extends BaseActivity implements HostContract.BsView {


    @BindView(R.id.toolbar)
    ToolBar toolbar;
    @BindView(R.id.edit_server)
    EditText editServer;
    @BindView(R.id.btn_server)
    Button btnServer;

    private HostActivity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        ButterKnife.bind(this);

        new HostPresenter(this).start();
    }

    @Override
    public void init() {
        toolbar.setTitle("服务器地址");
        toolbar.setExt("返回");
        toolbar.setExtListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });

        String server = AppInfo.getAppInfo().getServer();
        editServer.setText(server);
    }

    @OnClick(R.id.btn_server)
    void updateServer() {
        String string = editServer.getText().toString();
        AppInfo.getAppInfo().setServer(string);
    }

    @Override
    public void setPresenter(HostContract.Presenter presenter) {

    }
}
