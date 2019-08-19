package com.tokeninfo.ui.contract;

import com.tokeninfo.base.BasePresenter;
import com.tokeninfo.base.BaseView;

public interface MainContract {

    interface BsView extends BaseView<Presenter> {

        void fragment(int position);
    }

    interface Presenter extends BasePresenter {

<<<<<<< HEAD
        void pushToken(String token);

        void uploadTarget(String plat,String symbol,String price);

        void refreshList();

        void remove(String plat,String symbol,String pricce);
=======
        void deviceToken(String token);
>>>>>>> develop
    }
}
