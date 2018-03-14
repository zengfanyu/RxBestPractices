package com.zfy.rxbestpractices.presenter;

import android.Manifest;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zfy.rxbestpractices.base.BaseRxPresenter;
import com.zfy.rxbestpractices.contract.FullScreenContract;
import com.zfy.rxbestpractices.util.ImageUtil;
import com.zfy.rxbestpractices.util.LogUtil;

import javax.inject.Inject;

/**
 * @author: fanyuzeng on 2018/3/14 16:28
 */
public class FullScreenPresenter extends BaseRxPresenter<FullScreenContract.View> implements FullScreenContract.Presenter {
    private RxPermissions mRxPermissions;

    @Inject
    public FullScreenPresenter(RxPermissions rxPermissions) {
        mRxPermissions = rxPermissions;
    }


    @Override
    public void saveImg(Context context, Drawable imgDrawable, String imgUrl) {
        boolean isSaveSuccess = ImageUtil.saveImage(context, imgDrawable, imgUrl);

        if (isSaveSuccess) {
            mView.onSavePhotoSuccess();
        } else {
            mView.onSavePhotoFail();
        }

    }

    @Override
    public void checkPermission() {
        addSubscribe(mRxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(isGranted -> {
                    LogUtil.d(TAG, "accept aBoolean=" + isGranted);
                    // 当所有权限都允许之后，返回true
                    if (isGranted) {
                        mView.onRequestGranted();
                    }
                    // 只要有一个权限禁止，返回false，
                    // 下一次申请只申请没通过申请的权限
                    else {
                        mView.onRequestNotGranted();
                    }
                }));
    }

    @Override
    public void setupPhotoAttacher() {
        mView.onSetupPhotoAttacher();
    }

    @Override
    public void setupTagView() {
        mView.onShowSaveTag();
    }
}
