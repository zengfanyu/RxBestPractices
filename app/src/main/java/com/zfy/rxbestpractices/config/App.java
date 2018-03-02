package com.zfy.rxbestpractices.config;

import android.app.Application;

import com.zfy.rxbestpractices.di.component.AppComponent;
import com.zfy.rxbestpractices.di.component.DaggerAppComponent;
import com.zfy.rxbestpractices.di.module.AppModule;
import com.zfy.rxbestpractices.di.module.HttpModule;

/**
 * @author: fanyuzeng on 2018/2/28 16:16
 */
public class App extends Application {

    private static AppComponent mAppComponent;
    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance=this;
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .httpModule(new HttpModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static App getInstance() {
        return sInstance;
    }
}
