package com.zfy.rxbestpractices.di.module;

import android.app.Activity;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zfy.rxbestpractices.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author: fanyuzeng on 2018/3/1 17:21
 */
@Module
public class MainActivityModule {
    private Activity activity;

    public MainActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    RxPermissions provideRxPermissions(Activity activity) {
        return new RxPermissions(activity);
    }
}
