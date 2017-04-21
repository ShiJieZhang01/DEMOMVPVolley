package com.yang.okhttpmvpdemo.okhttp.listener;

import java.util.ArrayList;

/**
 * Created by admin on 17/4/21.
 */

public interface DisposeHandleCookieListener extends DisposeDataListener{
    public void onCookie(ArrayList<String> cookieStrLists);
}
