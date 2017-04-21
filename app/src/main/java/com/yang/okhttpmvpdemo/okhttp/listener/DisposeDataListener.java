package com.yang.okhttpmvpdemo.okhttp.listener;

/**
 * Created by admin on 17/4/21.
 */

public interface DisposeDataListener {
    /**
     * 请求成功回调事件处理
     */
    public void onSuccess(Object responseObj);

    /**
     * 请求失败回调事件处理
     */
    public void onFailure(Object reasonObj);
}
