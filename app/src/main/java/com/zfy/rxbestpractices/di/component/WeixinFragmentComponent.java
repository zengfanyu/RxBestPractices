package com.zfy.rxbestpractices.di.component;

import com.zfy.rxbestpractices.di.module.WeixinFragmentModule;
import com.zfy.rxbestpractices.di.scope.FragmentScope;
import com.zfy.rxbestpractices.weixin.WeChatFragment;

import dagger.Component;

/**
 * 提供 WeChatFragment 页面的实例
 * @author: fanyuzeng on 2018/3/1 17:08
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = WeixinFragmentModule.class)
public interface WeixinFragmentComponent {
    /**
     * 将实例注入到 WeChatFragment 中需要注入的属性中
     * @param weChatFragment
     */
    void inject(WeChatFragment weChatFragment);

}
