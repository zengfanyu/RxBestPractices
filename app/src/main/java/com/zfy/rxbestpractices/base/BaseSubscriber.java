package com.zfy.rxbestpractices.base;

import android.content.Context;

import com.zfy.rxbestpractices.R;
import com.zfy.rxbestpractices.util.LogUtil;
import com.zfy.rxbestpractices.util.NetworkUtil;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * @author: fanyuzeng on 2018/3/1 17:01
 */
public abstract class BaseSubscriber<T> extends ResourceSubscriber<T> {
    private static final String TAG = "-BaseSubscriber-";
    private Context mContext;

    private IBaseView mBaseView;

    public BaseSubscriber(Context context, IBaseView mBaseView) {
        this.mContext = context;
        this.mBaseView = mBaseView;
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(TAG, "onStart");
        if (!NetworkUtil.isNetworkConnected(mContext)) {
            LogUtil.d(TAG, "onStart no net");
            mBaseView.showErrorTip(mContext.getString(R.string.no_network));
            onComplete();
        }
        // TODO(ZFY): 2018/3/2 有网络情况下做其他操作
    }

    @Override
    public void onError(Throwable t) {
        LogUtil.e(TAG, "onError:" + t);
//        mBaseView.showErrorTip(t.getMessage());
    }

    @Override
    public void onComplete() {
        LogUtil.d(TAG, "onComplete");
    }
}

