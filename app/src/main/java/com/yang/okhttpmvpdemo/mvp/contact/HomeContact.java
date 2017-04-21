package com.yang.okhttpmvpdemo.mvp.contact;

import com.yang.okhttpmvpdemo.mvp.presenter.BasePresenter;
import com.yang.okhttpmvpdemo.mvp.view.BaseMvpView;
import com.yang.okhttpmvpdemo.util.BusinessBean;

import java.util.List;

/**
 * Created by admin on 17/4/21.
 */

public interface HomeContact {
    interface Presenter extends BasePresenter{
        void showname();
        void okhttpget();
        void okhttpPost();
        void getBussinessfromvolloy();
    }
    interface MvpView extends BaseMvpView<Presenter>{
        void updateBussiness(List<BusinessBean.Business> businesseslist);
    }
}
