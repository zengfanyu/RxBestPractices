package com.zfy.rxbestpractices.base;

import com.zfy.rxbestpractices.util.LogUtil;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * MVP 架构 Presenter 的基类， 对 RxJava 异步事件统一管理，防止内存泄漏
 *
 * @author: fanyuzeng on 2018/3/1 14:30
 */
public class BaseRxPresenter<T extends IBaseView> implements IBasePresenter<T> {
    protected String TAG = "-" + this.getClass().getSimpleName() + "-";

    protected T mView;

    private CompositeDisposable mCompositeDisposable;

    /**
     * 订阅事件
     *
     * @param
     */
    protected void addSubscribe(Disposable disposable) {
        LogUtil.d(TAG, "addSubscribe");
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    /**
     * 取消所有订阅，防止内存泄漏
     */
    protected void unSubscribe() {
        LogUtil.d(TAG, "unSubscribe");
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void takeView(T view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
        unSubscribe();
    }
}
