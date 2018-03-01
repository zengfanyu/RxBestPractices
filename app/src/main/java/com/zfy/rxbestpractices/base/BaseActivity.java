package com.zfy.rxbestpractices.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zfy.rxbestpractices.util.ActivityUtil;

import butterknife.ButterKnife;

/**
 * Activity 的基类
 *
 * @author: fanyuzeng on 2018/3/1 10:23
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        ActivityUtil.getInstance().pushActivity(this);
        mActivity = this;
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
     * 视图树创建完成，onCreate方法中调用
     */
    protected void onViewCreated() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtil.getInstance().removeActivity(this);
    }
}
