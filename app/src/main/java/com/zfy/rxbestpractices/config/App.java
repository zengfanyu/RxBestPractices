package com.zfy.rxbestpractices.config;

import android.app.Application;

import com.tencent.smtt.sdk.QbSdk;
import com.zfy.rxbestpractices.di.component.AppComponent;
import com.zfy.rxbestpractices.di.component.DaggerAppComponent;
import com.zfy.rxbestpractices.di.module.AppModule;
import com.zfy.rxbestpractices.di.module.HttpModule;
import com.zfy.rxbestpractices.util.LogUtil;

/**
 * @author: fanyuzeng on 2018/2/28 16:16
 */
public class App extends Application {
    private static final String TAG = "-App-";

    private static AppComponent mAppComponent;
    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .httpModule(new HttpModule())
                .build();
        initX5();
        initLeakCanary();
    }


    private void initLeakCanary() {
        // TODO(ZFY): 2018/3/5
    }

    private void initX5() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                LogUtil.i(TAG, " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static App getInstance() {
        return sInstance;
    }
}
