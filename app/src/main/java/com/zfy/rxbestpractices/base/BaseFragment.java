package com.zfy.rxbestpractices.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zfy.rxbestpractices.util.LogUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment 的基类<p>
 *
 * @author: fanyuzeng on 2018/3/1 14:56
 */
public abstract class BaseFragment extends Fragment {
    protected final String TAG = "-" + this.getClass().getSimpleName() + "-";
    protected View mContentView;
    protected Context mContext;
    protected Activity mActivity;
    private Unbinder mBind;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.d(TAG, "onAttach");
        mActivity = (Activity) context;
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.d(TAG, "onCreateView");
        mContentView = inflater.inflate(getContentLayoutRes(), null);
        mBind = ButterKnife.bind(this, mContentView);
        initViews();
        return mContentView;
    }

    /**
     * 获取Fragment布局的ID,由子类实现
     *
     * @return
     */
    protected abstract int getContentLayoutRes();

    /**
     * 初始化布控件
     */
    protected abstract void initViews();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }
}
