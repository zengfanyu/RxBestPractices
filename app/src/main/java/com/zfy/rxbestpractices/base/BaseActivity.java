package com.zfy.rxbestpractices.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zfy.rxbestpractices.util.ActivityUtil;
import com.zfy.rxbestpractices.util.LogUtil;

import butterknife.ButterKnife;

/**
 * Activity 的基类
 *
 * @author: fanyuzeng on 2018/3/1 10:23
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected String TAG = "-" + this.getClass().getSimpleName() + "-";
    protected Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(TAG, "onCreate");
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        ActivityUtil.getInstance().pushActivity(this);
        onViewCreated();
        setTitle("");
        initViews();
    }

    /**
     * 初始化View
     */
    protected abstract void initViews();


    /**
     * 提供子类的布局文件id
     *
     * @return
     */
    public abstract int getContentViewId();

    /**
     * ButterKnife.bind 之后，initViews 之前
     */
    protected abstract void onViewCreated() ;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtil.getInstance().removeActivity(this);
    }
}
