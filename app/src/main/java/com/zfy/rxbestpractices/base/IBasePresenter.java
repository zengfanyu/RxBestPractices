package com.zfy.rxbestpractices.base;

/**
 * @author: fanyuzeng on 2018/3/1 11:38
 */
public interface IBasePresenter<T extends IBaseView> {

    /**
     * 将View绑定至Presenter的方法
     *
     * @param view the view associated with this presenter
     */
    void takeView(T view);

    /**
     * 解除 V 和 P 的绑定更关系，
     */
    void dropView();

}
