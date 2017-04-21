package com.yang.okhttpmvpdemo.mvp.view;

import android.content.Context;

/**
 * Created by admin on 17/4/21.
 */

public interface BaseMvpView<T> {
    void attachPresenter(T presenter);
    Context getContext();
}
