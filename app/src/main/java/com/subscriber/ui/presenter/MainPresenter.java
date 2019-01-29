package com.subscriber.ui.presenter;

import com.subscriber.ui.contract.MainContract;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.BsView bsView;

    public MainPresenter(MainContract.BsView bsView){
        this.bsView = bsView;
        this.bsView.setPresenter(this);
    }

    @Override
    public void start() {
        bsView.init();
    }
}
