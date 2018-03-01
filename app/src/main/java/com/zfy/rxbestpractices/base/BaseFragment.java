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

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment 的基类<p>
 * 生命周期方法：onAttach()->onCreate()->onCreateView()->onViewCreated()
 *
 * @author: fanyuzeng on 2018/3/1 14:56
 */
public abstract class BaseFragment extends Fragment {

    protected View mContentView;
    protected Context mContext;
    protected Activity mActivity;
    private Unbinder mBind;
    private boolean isInited = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(getContentLayoutRes(), null);
        mBind = ButterKnife.bind(this, mContentView);
        isInited = true;
        initViews();
        return mContentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();

    }

    protected abstract void initData();

    protected abstract int getContentLayoutRes();

    protected abstract void initViews();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();

    }
}
