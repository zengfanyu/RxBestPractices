package com.zfy.rxbestpractices.presenter;

import android.Manifest;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zfy.rxbestpractices.base.RxPresenter;
import com.zfy.rxbestpractices.contract.MainContract;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * @author: fanyuzeng on 2018/3/1 17:19
 */
public class MainPersenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {
    private RxPermissions rxPermissions;

    @Inject
    public MainPersenter(RxPermissions rxPermissions) {
        this.rxPermissions = rxPermissions;
    }

    @Override
    public void checkPermissions() {
        addSubscribe(rxPermissions.request(Manifest.permission.READ_PHONE_STATE
                , Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.READ_EXTERNAL_STORAGE
                , Manifest.permission.ACCESS_COARSE_LOCATION
                , Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        // 当所有权限都允许之后，返回true
                        if (aBoolean) {
                            mView.getPermissionSuccess();
                        }
                        // 只要有一个权限禁止，返回false，
                        // 下一次申请只申请没通过申请的权限
                        else {
                            mView.showPermissionDialog();
                        }
                    }
                }));
    }
}
