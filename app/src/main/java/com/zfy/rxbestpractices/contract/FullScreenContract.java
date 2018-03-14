package com.zfy.rxbestpractices.contract;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.zfy.rxbestpractices.base.IBasePresenter;
import com.zfy.rxbestpractices.base.IBaseView;

/**
 * @author: fanyuzeng on 2018/3/14 16:19
 */
public interface FullScreenContract {

    interface View extends IBaseView {
        /**
         * 显示保存 dialog
         */
        void onShowSaveTag();

        /**
         * 用户授予权限
         */
        void onRequestGranted();

        /**
         * 用于未授予权限
         */
        void onRequestNotGranted();

        /**
         * 初始化 PhotoAttacher
         */
        void onSetupPhotoAttacher();

        /**
         * 保存图片成功
         */
        void onSavePhotoSuccess();

        /**
         * 保存图片失败
         */
        void onSavePhotoFail();
    }

    interface Presenter extends IBasePresenter<View> {
        /**
         * 保存图片到本地
         * @param context
         * @param imgDrawable 要保存的 Drawable
         * @param imgUrl 作为文件名
         */
        void saveImg(Context context, Drawable imgDrawable, String imgUrl);

        /**
         * 获取权限
         */
        void checkPermission();

        /**
         * 通知View层初始化 PhotoAttacher
         */
        void setupPhotoAttacher();

        /**
         * 通知 View 层显示保存Dialog
         */
        void setupTagView();
    }
}
