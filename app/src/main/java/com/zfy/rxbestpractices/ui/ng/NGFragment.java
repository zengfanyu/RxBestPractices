package com.zfy.rxbestpractices.ui.ng;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.zfy.rxbestpractices.R;
import com.zfy.rxbestpractices.base.BaseMVPFragment;
import com.zfy.rxbestpractices.config.App;
import com.zfy.rxbestpractices.contract.NGContract;
import com.zfy.rxbestpractices.di.component.DaggerNGFragmentComponent;
import com.zfy.rxbestpractices.di.module.NGFragmentModule;
import com.zfy.rxbestpractices.http.api.NGApi;
import com.zfy.rxbestpractices.http.bean.NGBean;
import com.zfy.rxbestpractices.presenter.NGPresenter;
import com.zfy.rxbestpractices.util.LogUtil;

import java.util.List;

import butterknife.BindView;

/**
 * @author: fanyuzeng on 2018/3/13 15:21
 */
public class NGFragment extends BaseMVPFragment<NGPresenter> implements NGContract.View,
        SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.id_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.id_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeLayout;

    private NGAdapter mNGAdapter;
    private boolean isDataRefresh;

    public static NGFragment newInstance() {
        return new NGFragment();
    }

    @Override
    protected void inject() {
        DaggerNGFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .nGFragmentModule(new NGFragmentModule())
                .build().inject(this);
    }

    @Override
    protected int getContentLayoutRes() {
        return R.layout.fragment_ng;
    }

    @Override
    protected void initViews() {
        mSwipeLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeLayout.setRefreshing(true);
        mSwipeLayout.setOnRefreshListener(this);

        // TODO(ZFY): 2018/3/13
        mNGAdapter = new NGAdapter(R.layout.item_ng);
        mNGAdapter.openLoadAnimation();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mRecyclerView.setAdapter(mNGAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mNGAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId()==R.id.id_ng_img){
                LogUtil.d(TAG, "item child img:" + " position:" + position + " clicked!");
            }else if (view.getId()==R.id.id_ng_title){
                LogUtil.d(TAG, "item child title :" + " position:" + position + " clicked!");
            }else {
                LogUtil.d(TAG, "item child content:" + " position:" + position + " clicked!");
            }

        });
    }

    @Override
    public void showNGData(List<NGBean> data) {
        LogUtil.d(TAG, "showWeCahtData");
        if (mSwipeLayout != null && mSwipeLayout.isRefreshing()) {
            mSwipeLayout.setRefreshing(false);
        }

        mNGAdapter.setNewData(data);
        LogUtil.d(TAG, "刷新成功");
        if (isDataRefresh) {
            showMsgTip("刷新成功");
            isDataRefresh = false;
        }
    }

    @Override
    public void getNGDataFailed(String msg) {
        mNGAdapter.loadMoreFail();
        mSwipeLayout.setEnabled(true);
        mSwipeLayout.setRefreshing(false);
//        showErrorTip(msg);
        showErrorView(R.layout.empty_view);
    }

    @Override
    public void showBigPic() {
        // TODO(ZFY): 2018/3/13 点击图片全屏可缩放
    }

    @Override
    public void showPicDesc() {
        // TODO(ZFY): 2018/3/13 点击文字进入详情页面
    }

    @Override
    protected void initData() {
        mPresenter.getData(NGApi.URL);
    }

    @Override
    public void onRefresh() {
        //swipe
        mPresenter.getData(NGApi.URL);
        isDataRefresh = true;
    }


}
