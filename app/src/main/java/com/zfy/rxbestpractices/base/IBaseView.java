package com.zfy.rxbestpractices.base;

/**
 * MVP架构中，View 层的接口
 *
 * @author: fanyuzeng on 2018/3/1 10:20
 */
public interface IBaseView {
    /**
     * 显示正常信息
     * @param msg
     */
    void showMsgTip(String msg);

    /**
     * 可定制SnackBar显示信息的背景和文字颜色
     * @param msg
     * @param messageColor
     * @param backgroundColor
     */
    void showMsgTip(String msg,int messageColor,int backgroundColor);

    /**
     * 显示异常信息
     * @param msg
     */
    void showErrorTip(String msg);

    /**
     * 加载数据错误使，展示的界面
     * @param viewLayoutId
     */
    void showErrorView(int viewLayoutId);

    /**
     *开始加载
     */
    void startLoading();

    /**
     *停止加载
     */
    void stopLoading();

}
