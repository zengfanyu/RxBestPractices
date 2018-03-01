package com.zfy.rxbestpractices.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author: fanyuzeng on 2018/3/1 14:30
 */
public class RxPresenter<T extends IBaseView> implements IBasePresenter<T> {

    protected T mView;

    protected CompositeDisposable mCompositeDisposable;

    /**
     * 订阅事件
     *
     * @param
     */
    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    /**
     * 取消所有订阅
     */
    protected void unSubscribe() {
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
