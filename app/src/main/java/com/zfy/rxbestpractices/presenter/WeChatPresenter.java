package com.zfy.rxbestpractices.presenter;

import android.content.Context;

import com.zfy.rxbestpractices.base.BaseRxPresenter;
import com.zfy.rxbestpractices.base.BaseSubscriber;
import com.zfy.rxbestpractices.config.Constants;
import com.zfy.rxbestpractices.contract.WeChatContract;
import com.zfy.rxbestpractices.http.api.WeixinApi;
import com.zfy.rxbestpractices.http.bean.WeixinBean;
import com.zfy.rxbestpractices.util.LogUtil;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: fanyuzeng on 2018/3/1 15:22
 */
public class WeChatPresenter extends BaseRxPresenter<WeChatContract.View> implements WeChatContract.Presenter {
    private WeixinApi mWeixinApi;
    private Context mContext;

    @Inject
    public WeChatPresenter(WeixinApi weixinApi, Context context) {
        mWeixinApi = weixinApi;
        mContext = context;
    }

    @Override
    public void getWeChatData(int pageSize, int pageIndex) {
        LogUtil.d(TAG, "getWeChatData ");
        addSubscribe(mWeixinApi.getWeiXin(Constants.WEIXIN_API_KEY, pageSize, pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseSubscriber<WeixinBean>(mContext, mView) {
                    @Override
                    public void onNext(WeixinBean weiXinBean) {
                        LogUtil.d(TAG, "onNext result:"+weiXinBean.toString());
                        if (weiXinBean.getCode()==Constants.HTTP_OK) {
                            mView.showWeCahtData(weiXinBean);
                        }else {
                            mView.getDataFail("response code:"+weiXinBean.getCode()+" response msg:"+weiXinBean.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        LogUtil.e(TAG, "onError");
                        mView.getDataFail(t.getMessage());
                    }
                }));
    }
}
