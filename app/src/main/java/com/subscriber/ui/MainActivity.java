package com.subscriber.ui;

import android.app.Activity;
import android.os.Bundle;

import com.subscriber.R;
import com.subscriber.base.BaseActivity;
import com.subscriber.ui.contract.MainContract;
import com.subscriber.ui.presenter.MainPresenter;

public class MainActivity extends BaseActivity implements MainContract.BsView {

    private MainActivity activity;
    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MainPresenter(this).start();
    }

    @Override
    public void init() {
        activity = this;
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
