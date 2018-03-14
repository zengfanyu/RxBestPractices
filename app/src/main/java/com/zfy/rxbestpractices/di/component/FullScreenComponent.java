package com.zfy.rxbestpractices.di.component;

import com.zfy.rxbestpractices.di.module.FullScreenModule;
import com.zfy.rxbestpractices.di.scope.ActivityScope;
import com.zfy.rxbestpractices.ui.fullscreenimg.FullScreenImageActivity;

import dagger.Component;

/**
 * @author: fanyuzeng on 2018/3/14 16:40
 */
@ActivityScope
@Component(modules = FullScreenModule.class)
public interface FullScreenComponent {
    /**
     * @param activity
     */
    void inject(FullScreenImageActivity activity);
}
