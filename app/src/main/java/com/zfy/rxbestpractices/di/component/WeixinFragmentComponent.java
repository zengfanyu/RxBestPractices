package com.zfy.rxbestpractices.di.component;

import com.zfy.rxbestpractices.di.module.WeixinFragmentModule;
import com.zfy.rxbestpractices.di.scope.FragmentScope;
import com.zfy.rxbestpractices.weixin.WeixinFragment;

import dagger.Component;

/**
 * @author: fanyuzeng on 2018/3/1 17:08
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = WeixinFragmentModule.class)
public interface WeixinFragmentComponent {

    void inject(WeixinFragment weixinFragment);

}
