package com.tokeninfo.ui.presenter;

import com.tokeninfo.ui.contract.HostContract;

public class HostPresenter implements HostContract.Presenter {

    private HostContract.BsView baseView;

    public HostPresenter(HostContract.BsView bsView){
        this.baseView = bsView;
        bsView.init();
    }

    @Override
    public void start() {

    }
}
