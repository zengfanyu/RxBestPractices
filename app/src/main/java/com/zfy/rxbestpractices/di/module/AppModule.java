package com.zfy.rxbestpractices.di.module;

import android.content.Context;

import com.zfy.rxbestpractices.config.App;
import com.zfy.rxbestpractices.db.GreenDaoManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author: fanyuzeng on 2018/3/1 12:04
 */
@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    GreenDaoManager provideGreenDaoManager(Context context) {
        return new GreenDaoManager(context);
    }
}
