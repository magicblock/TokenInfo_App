package com.tokeninfo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.PushCallback;
import com.PushSDK;
import com.tokeninfo.R;
import com.tokeninfo.base.BaseActivity;
import com.tokeninfo.ui.adapter.TargetAdapter;
import com.tokeninfo.ui.bean.TargetBean;
import com.tokeninfo.ui.contract.MainContract;
import com.tokeninfo.ui.presenter.MainPresenter;
import com.tokeninfo.util.share.AppInfo;
import com.tokeninfo.widget.ToolBar;

import java.util.List;

public class MainActivity extends BaseActivity implements MainContract.BsView {

    @BindView(R.id.toolbar)
    ToolBar toolbar;
    @BindView(R.id.edit_symbol)
    EditText editSymbol;
    @BindView(R.id.edit_price)
    EditText editPrice;
    @BindView(R.id.btn_upload)
    Button btnUpload;
    @BindView(R.id.btn_refresh)
    Button btnRefresh;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.btn_okex)
    RadioButton btnOkex;
    @BindView(R.id.btn_binance)
    RadioButton btnBinance;
    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;

    private MainActivity activity;
    private MainContract.Presenter presenter;

    private TargetAdapter targetAdapter;

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
        toolbar.setExt("设置");
        toolbar.setExtListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(activity, SetActivity.class);
                activity.startActivity(intent);
            }
        });

        PushSDK.getPushSDK().connnect(activity, new PushCallback() {

            @Override
            public void token(String token) {
                AppInfo.getAppInfo().setPushToken(token);
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(activity);
        recyclerview.setLayoutManager(manager);
        targetAdapter = new TargetAdapter();
        recyclerview.setAdapter(targetAdapter);
        targetAdapter.setCallBack(new TargetAdapter.CallBack() {

            @Override
            public void remove(TargetBean bean) {
                presenter.remove(bean.getPlat(),bean.getSymbol(), String.valueOf(bean.getPrice()));
            }
        });
    }

    @Override
    public void showTargets(List<TargetBean> targetBeans) {
        targetAdapter.setBeanList(targetBeans);
    }

    @OnClick({R.id.btn_refresh, R.id.btn_upload})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_upload:
                String plat = btnOkex.isChecked() ? "okex" : "binance";
                String symbol = editSymbol.getText().toString();
                String price = editPrice.getText().toString();
                presenter.uploadTarget(plat, symbol, price);
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
