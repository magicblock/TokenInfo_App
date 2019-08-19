package com.tokeninfo.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.PushCallback;
import com.PushSDK;
import com.tokeninfo.R;
import com.tokeninfo.ui.contract.MainContract;
import com.tokeninfo.ui.fragment.MarginFragment;
import com.tokeninfo.ui.presenter.MainPresenter;
import com.tokeninfo.util.share.AppInfo;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.BsView {

    @BindView(R.id.txt_spot)
    TextView txtSpot;
    @BindView(R.id.txt_margin)
    TextView txtMargin;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;

    MainActivity activity;
    MainContract.Presenter presenter;

    MarginFragment marginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        new MainPresenter(this).start();
    }

    @Override
    public void init() {
        activity = this;

        PushSDK.getPushSDK().connnect(activity, new PushCallback() {

            @Override
            public void token(final String token) {
                presenter.deviceToken(token);
            }
        });

        marginFragment = MarginFragment.fragment();
        fragment(0);

        AppInfo.getAppInfo().setServer("47.244.139.127");
        // AppInfo.getAppInfo().setServer("192.168.40.75");
    }

    @OnClick(R.id.txt_spot)
    void spot() {
        fragment(0);
    }

    @OnClick(R.id.txt_margin)
    void margin() {
        fragment(1);
    }

    @Override
    public void fragment(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // TODO: 2018/5/8 这个还是得换下
        @SuppressLint("RestrictedApi") List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment.isVisible()) {
                    fragmentTransaction.hide(fragment);
                }
            }
        }

        if (position == 0) {
            if (!marginFragment.isAdded()) {
                fragmentTransaction.add(R.id.framelayout, marginFragment);
            } else {
                fragmentTransaction.show(marginFragment);
            }
        }

        //commit :IllegalStateException: Can not perform this action after onSaveInstanceState
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
