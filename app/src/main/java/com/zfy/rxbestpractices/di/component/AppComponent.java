package com.zfy.rxbestpractices.di.component;

import android.content.Context;

import com.zfy.rxbestpractices.db.GreenDaoManager;
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
     * 获取 ApplicationContext 全局单例类
     *
     * @return
     */
    Context getAppContext();

    /**
     * 获取 {@link Retrofit.Builder} 全局单例类
     *
     * @return
     */
    Retrofit.Builder getRetrofitBuilder();

    /**
     * 获取 {@link OkHttpClient} 全局单例类
     *
     * @return
     */
    OkHttpClient getOkHttpClient();

    /**
     * 获取 {@link GreenDaoManager} 全局单立磊
     *
     * @return
     */
    GreenDaoManager getGreenDaoManager();
}
