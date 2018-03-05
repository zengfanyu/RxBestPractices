package com.zfy.rxbestpractices.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.tencent.smtt.sdk.WebView;

/**
 * 腾讯WebView，对滚动进行监听。<p>
 * <a href=https://x5.tencent.com/tbs/guide/sdkInit.html#>腾讯浏览器服务</a>
 *
 * @author: fanyuzeng on 2018/3/5 11:29
 */
public class X5WebView extends WebView {
    private OnScrollChangeListener mScrollChangeListener;

    public interface OnScrollChangeListener {
        /**
         * 页面滚动回调
         *
         * @param l
         * @param t
         * @param oldl
         * @param oldt
         */
        void onScroll(int l, int t, int oldl, int oldt);
    }

    /**
     * {@link android.view.View#setOnScrollChangeListener(View.OnScrollChangeListener)} 对 API level 有限制，
     * 所以此处自定义回调。
     *
     * @param scrollChangeListener
     */
    public void setOnScrollChangeListener(OnScrollChangeListener scrollChangeListener) {
        mScrollChangeListener = scrollChangeListener;
    }

    public X5WebView(Context context) {
        this(context, null);
    }

    public X5WebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        mScrollChangeListener.onScroll(l, t, oldl, oldt);
    }
}
