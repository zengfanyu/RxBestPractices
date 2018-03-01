package com.zfy.rxbestpractices.base;

/**
 * @author: fanyuzeng on 2018/3/1 11:38
 */
public interface IBasePresenter<T extends IBaseView> {

    /**
     * Binds presenter with a view when resumed. The Presenter will perform initialization here.
     *
     * @param view the view associated with this presenter
     */
    void takeView(T view);

    /**
     * Drops the reference to the view when destroyed
     */
    void dropView();

}
