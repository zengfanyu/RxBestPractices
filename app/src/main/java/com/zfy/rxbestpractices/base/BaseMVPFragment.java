package com.zfy.rxbestpractices.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zfy.rxbestpractices.util.SnackBarUtil;

import javax.inject.Inject;

/**
 * @author: fanyuzeng on 2018/3/1 14:56
 */
public abstract class BaseMVPFragment<T extends IBasePresenter> extends BaseFragment implements IBaseView {

    @Inject
    protected T mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initInject();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // MARK(ZFY): 区别
//        initInject();
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter != null) {
            mPresenter.takeView(this);
        }
    }

    protected abstract void initInject();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.dropView();
        }
    }

    @Override
    public void showMsg(String msg) {
        SnackBarUtil.ShortSnackbar(((ViewGroup) mActivity.findViewById(android.R.id.content)).getChildAt(0), msg, SnackBarUtil.INFO).show();

    }

    @Override
    public void showError(String msg) {
        SnackBarUtil.ShortSnackbar(((ViewGroup) mActivity.findViewById(android.R.id.content)).getChildAt(0), msg, SnackBarUtil.ERROR);

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
