package com.zfy.rxbestpractices.presenter;

import android.content.Context;

import com.zfy.rxbestpractices.base.BaseSubscriber;
import com.zfy.rxbestpractices.base.RxPresenter;
import com.zfy.rxbestpractices.config.Constants;
import com.zfy.rxbestpractices.contract.WeixinContract;
import com.zfy.rxbestpractices.http.api.WeixinApi;
import com.zfy.rxbestpractices.http.bean.WeixinBean;
import com.zfy.rxbestpractices.util.LogUtil;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: fanyuzeng on 2018/3/1 15:22
 */
public class WeixinPresenter extends RxPresenter<WeixinContract.View> implements WeixinContract.Presenter {
    private static final String TAG = "==WeixinPresenter==";
    private WeixinApi mWeixinApi;
    private Context mContext;

    @Inject
    public WeixinPresenter(WeixinApi weixinApi, Context context) {
        mWeixinApi = weixinApi;
        mContext = context;
    }

    @Override
    public void getWeixinData(int pageSize, int pageIndex) {
        LogUtil.d(TAG, "getWeixinData mView:"+mView);
        addSubscribe(mWeixinApi.getWeiXin(Constants.WEIXIN_API_KEY, pageSize, pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseSubscriber<WeixinBean>(mContext, mView) {
                    @Override
                    public void onNext(WeixinBean weiXinBean) {
                        LogUtil.d(TAG, "onNext");
                        mView.showWeixinData(weiXinBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        mView.getDataFail();
                    }
                }));
    }
}
