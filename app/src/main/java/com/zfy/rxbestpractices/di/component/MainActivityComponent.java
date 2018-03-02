package com.zfy.rxbestpractices.di.component;

import com.zfy.rxbestpractices.MainActivity;
import com.zfy.rxbestpractices.di.module.MainActivityModule;
import com.zfy.rxbestpractices.di.scope.ActivityScope;

import dagger.Component;

/**
 * 提供 MainActivity 的实例
 *
 * @author: fanyuzeng on 2018/3/1 17:21
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {
    /**
     * 将实例注入到 MainActicity 中需要注入的属性中
     *
     * @param mainActivity mainActivity
     */
    void inject(MainActivity mainActivity);
}
