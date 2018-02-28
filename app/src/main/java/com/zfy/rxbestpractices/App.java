package com.zfy.rxbestpractices;

import android.app.Application;

/**
 * @author: fanyuzeng on 2018/2/28 16:16
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Common.mAppContext = this;
    }


}
