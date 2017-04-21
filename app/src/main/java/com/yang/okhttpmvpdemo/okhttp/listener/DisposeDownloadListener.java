package com.yang.okhttpmvpdemo.okhttp.listener;

/**
 * Created by admin on 17/4/21.
 */

public interface DisposeDownloadListener extends DisposeDataListener{
    public void onProgress(int progrss);
}
