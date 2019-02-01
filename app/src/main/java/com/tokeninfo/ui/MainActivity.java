package com.tokeninfo.ui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tokeninfo.R;
import com.tokeninfo.base.BaseActivity;
import com.tokeninfo.ui.bean.MessageEvent;
import com.tokeninfo.ui.contract.MainContract;
import com.tokeninfo.ui.presenter.MainPresenter;
import com.tokeninfo.util.TimeUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/main/main")
public class MainActivity extends BaseActivity implements MainContract.BsView {

    @BindView(R.id.txt_log)
    TextView txtLog;

    private MainActivity activity;
    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        EventBus.getDefault().register(this);

        new MainPresenter(this).start();
    }

    @Override
    public void init() {
        activity = this;

        PackageManager localPackageManager = getPackageManager();
        ComponentName componentName = new ComponentName(activity, TradeReceiveNotificationService.class);
        localPackageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        localPackageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }

    @Override
    public void enableNotifcation() {
        boolean enable = false;
        String str = getPackageName();
        String localObject = Settings.Secure.getString(getContentResolver(), "enabled_notification_listeners");
        if (!TextUtils.isEmpty(localObject)) {
            String[] strArr = (localObject).split(":");
            int i = 0;
            while (i < strArr.length) {
                ComponentName localComponentName = ComponentName.unflattenFromString(strArr[i]);
                if ((localComponentName != null) && (TextUtils.equals(str, localComponentName.getPackageName()))) {
                    enable = true;
                    break;
                }
                i += 1;
            }
        }

        if (!enable) {
            try {
                Intent intent;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
                } else {
                    intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
                }
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        TradeReceiveNotificationService.service(activity);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        String content = messageEvent.getContent();

        txtLog.append("\n");
        String show = getString(R.string.log_format, TimeUtil.getCurrentTimeInString(TimeUtil.DEFAULT_DATE_FORMAT), content);
        txtLog.append(show);
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
