package com.zfy.rxbestpractices.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.zfy.rxbestpractices.util.LogUtil;
import com.zfy.rxbestpractices.util.SnackBarUtil;

import javax.inject.Inject;

/**
 * MVP 中 View 层 Fragment 的基类，带 Dagger
 *
 * @author: fanyuzeng on 2018/3/1 14:56
 */
public abstract class BaseMVPFragment<T extends IBasePresenter> extends BaseFragment implements IBaseView {

    @Inject
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.takeView(this);
        }
        initData();
    }

    /**
     * 初始化数据的方法，由于是MVP架构，此方法会去访问网络，然后调用View层刷新UI，所以要在Presenter绑定View结束之后
     */
    protected abstract void initData();

    /**
     * Dagger 依赖注入,注入子页面需要依赖的实例对象，在使用其之前注入，也就是 initData 方法之前
     */
    protected abstract void inject();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.dropView();
        }
    }

    @Override
    public void showMsgTip(String msg) {
        LogUtil.d(TAG, "showMsgTip");
        SnackBarUtil.ShortSnackbar(((ViewGroup) mActivity.findViewById(android.R.id.content)).getChildAt(0), msg, SnackBarUtil.INFO).show();

    }

    @Override
    public void showErrorTip(String msg) {
        LogUtil.d(TAG, "showErrorTip");
        SnackBarUtil.ShortSnackbar(((ViewGroup) mActivity.findViewById(android.R.id.content)).getChildAt(0), msg, SnackBarUtil.ERROR).show();

    }

    @Override
    public void showErrorView(int viewLayoutId) {
        LogUtil.d(TAG, "showErrorView");
    }

    @Override
    public void startLoading() {
        LogUtil.d(TAG, "startLoading");
    }

    @Override
    public void stopLoading() {
        LogUtil.d(TAG, "stopLoading");
    }
}
