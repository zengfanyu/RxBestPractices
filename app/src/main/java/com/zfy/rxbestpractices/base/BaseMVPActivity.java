package com.zfy.rxbestpractices.base;

import android.view.ViewGroup;

import com.zfy.rxbestpractices.util.SnackBarUtil;

import javax.inject.Inject;

/**
 * MVP 中 View 层 Activity 的基类，带 Dagger
 *
 * @author: fanyuzeng on 2018/3/1 11:36
 */
public abstract class BaseMVPActivity<T extends IBasePresenter> extends BaseActivity implements IBaseView {

    @Inject
    protected T mPresenter;

    /**
     * Dagger 注入
     */
    protected abstract void initInject();

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null) {
            mPresenter.takeView(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.dropView();
        }
        super.onDestroy();
    }

    @Override
    public void showMsg(String msg) {
        SnackBarUtil.ShortSnackbar(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), msg, SnackBarUtil.INFO).show();
    }

    @Override
    public void showError(String msg) {
        SnackBarUtil.ShortSnackbar(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), msg, SnackBarUtil.ERROR);

    }

    @Override
    public void showEmptyView(int viewLayoutId) {

    }

    @Override
    public void startLoading() {

    }

    @Override
    public void stopLoading() {

    }

}
