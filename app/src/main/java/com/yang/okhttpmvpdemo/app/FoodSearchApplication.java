package com.yang.okhttpmvpdemo.app;

import android.app.Application;

/**
 * Created by admin on 17/4/21.
 */

public class FoodSearchApplication extends Application{
    private static FoodSearchApplication application;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
    public static FoodSearchApplication getInstance(){
        return application;
    }
}
