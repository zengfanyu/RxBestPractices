package com.zfy.rxbestpractices.contract;

import com.zfy.rxbestpractices.base.IBasePresenter;
import com.zfy.rxbestpractices.base.IBaseView;

/**
 * @author: fanyuzeng on 2018/3/1 17:14
 */
public interface MainContract {

    interface View extends IBaseView {
        /**
         * 未获取权限，弹出提示框
         */
        void showPermissionDialog();

        /**
         * 获取权限成功
         */
        void getPermissionSuccess();
    }

    interface Presenter extends IBasePresenter<View> {
        /**
         * 检查权限
         */
        void checkPermissions();
    }
}
