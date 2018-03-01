package com.zfy.rxbestpractices.base;

/**
 * MVP架构中，View 层的接口
 *
 * @author: fanyuzeng on 2018/3/1 10:20
 */
public interface IBaseView {
    void showMsg(String msg);

    void showError(String msg);

    void showEmptyView(int viewLayoutId);

    void startLoading();

    void stopLoading();

}
