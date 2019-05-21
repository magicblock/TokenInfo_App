package com.tokeninfo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.PushCallback;
import com.PushSDK;
import com.tokeninfo.R;
import com.tokeninfo.base.BaseActivity;
import com.tokeninfo.base.BaseResult;
import com.tokeninfo.ui.adapter.RecordAdapter;
import com.tokeninfo.ui.bean.RecordBean;
import com.tokeninfo.ui.contract.MainContract;
import com.tokeninfo.ui.presenter.MainPresenter;
import com.tokeninfo.util.SystermUtil;
import com.tokeninfo.util.ToastUtil;
import com.tokeninfo.util.share.AppInfo;
import com.tokeninfo.widget.ToolBar;

import java.util.List;

public class MainActivity extends BaseActivity implements MainContract.BsView {

    @BindView(R.id.toolbar)
    ToolBar toolbar;
    @BindView(R.id.txt_account)
    TextView txtAccount;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;


    private MainActivity activity;
    private MainContract.Presenter presenter;

    private RecordAdapter recordAdapter;

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
                        presenter.deviceToken(token);
                    }
                });
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recordAdapter = new RecordAdapter(activity);
        recyclerview.setAdapter(recordAdapter);

        //AppInfo.getAppInfo().setServer("47.244.139.127");10.31.36.245
        AppInfo.getAppInfo().setServer("10.31.36.245");
        if (!TextUtils.isEmpty(AppInfo.getAppInfo().getServer())) {
            presenter.account(new BaseResult<String>() {
                @Override
                public void success(String s) {
                    txtAccount.setText(s);
                }

                @Override
                public void fail(String string) {

                }
            });

            presenter.records(new BaseResult<List<RecordBean>>() {
                @Override
                public void success(List<RecordBean> recordBeans) {
                    recordAdapter.setData(recordBeans);
                }

                @Override
                public void fail(String string) {

                }
            });
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
