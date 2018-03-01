package com.zfy.rxbestpractices.di.component;

import com.zfy.rxbestpractices.MainActivity;
import com.zfy.rxbestpractices.di.module.MainActivityModule;
import com.zfy.rxbestpractices.di.scope.ActivityScope;

import dagger.Component;

/**
 * @author: fanyuzeng on 2018/3/1 17:21
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
