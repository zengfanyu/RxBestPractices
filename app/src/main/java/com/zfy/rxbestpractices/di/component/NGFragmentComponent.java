package com.zfy.rxbestpractices.di.component;

import com.zfy.rxbestpractices.di.module.NGFragmentModule;
import com.zfy.rxbestpractices.di.scope.FragmentScope;
import com.zfy.rxbestpractices.ui.ng.NGFragment;

import dagger.Component;

/**
 * @author: fanyuzeng on 2018/3/13 15:18
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = NGFragmentModule.class)
public interface NGFragmentComponent {

    void inject(NGFragment ngFragment);
}
