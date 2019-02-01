package com.tokeninfo.ui.presenter;

import com.tokeninfo.ui.contract.MainContract;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.BsView bsView;

    public MainPresenter(MainContract.BsView bsView){
        this.bsView = bsView;
        this.bsView.setPresenter(this);
    }

    @Override
    public void start() {
        bsView.init();
        bsView.enableNotifcation();
    }
}
