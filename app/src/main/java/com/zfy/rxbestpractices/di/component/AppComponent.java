package com.zfy.rxbestpractices.di.component;

import android.content.Context;

import com.zfy.rxbestpractices.di.module.AppModule;
import com.zfy.rxbestpractices.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * 为整个App提供单例类的实例
 *
 * @author: fanyuzeng on 2018/3/1 12:04
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    /**
     * @return
     */
    Context getContext();

    /**
     * @return
     */
    Retrofit.Builder getRetrofitBuilder();

    /**
     * @return
     */
    OkHttpClient getOkHttpClient();
}
