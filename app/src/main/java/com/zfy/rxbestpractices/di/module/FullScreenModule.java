package com.zfy.rxbestpractices.di.module;

import android.app.Activity;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zfy.rxbestpractices.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author: fanyuzeng on 2018/3/14 16:41
 */
@Module
public class FullScreenModule {
    private Activity mActivity;

    public FullScreenModule(Activity activity) {
        mActivity = activity;
    }


    @Provides
    @ActivityScope
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityScope
    RxPermissions provideRxPermission(Activity activity) {
        return new RxPermissions(activity);
    }
}
