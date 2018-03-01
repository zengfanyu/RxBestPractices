package com.zfy.rxbestpractices.di.module;

import com.zfy.rxbestpractices.di.scope.FragmentScope;
import com.zfy.rxbestpractices.http.api.WeixinApi;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: fanyuzeng on 2018/3/1 17:06
 */
@Module
public class WeixinFragmentModule {
    @Provides
    @FragmentScope
    Retrofit provideWeiXinRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return builder
                .baseUrl(WeixinApi.HOST)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @FragmentScope
    WeixinApi provideWeiXinApi(Retrofit retrofit) {
        return retrofit.create(WeixinApi.class);
    }
}
