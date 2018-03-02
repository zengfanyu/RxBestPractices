package com.zfy.rxbestpractices.weixin;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.zfy.rxbestpractices.R;
import com.zfy.rxbestpractices.base.BaseMVPFragment;
import com.zfy.rxbestpractices.config.App;
import com.zfy.rxbestpractices.contract.WeChatContract;
import com.zfy.rxbestpractices.di.component.DaggerWeixinFragmentComponent;
import com.zfy.rxbestpractices.di.module.WeixinFragmentModule;
import com.zfy.rxbestpractices.http.bean.WeixinBean;
import com.zfy.rxbestpractices.presenter.WeChatPresenter;
import com.zfy.rxbestpractices.util.LogUtil;

import butterknife.BindView;

/**
 * @author: fanyuzeng on 2018/3/1 15:35
 */
public class WeChatFragment extends BaseMVPFragment<WeChatPresenter> implements WeChatContract.View,
        SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.id_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.id_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeLayout;

    private int mPageIndex = 1;
    private static final int PAGE_SIZE = 16;

    private WeCahtAdapter mAdapter;

    private boolean isDataRefresh = false;

    public static WeChatFragment newInstance() {
        return new WeChatFragment();
    }

    @Override
    protected void initData() {
        mPresenter.getWeChatData(PAGE_SIZE, mPageIndex);
    }

    @Override
    protected int getContentLayoutRes() {
        return R.layout.fragment_weixin;
    }

    @Override
    protected void inject() {
        DaggerWeixinFragmentComponent
                .builder()
                .appComponent(App.getAppComponent())
                .weixinFragmentModule(new WeixinFragmentModule())
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        mSwipeLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeLayout.setRefreshing(true);
        mSwipeLayout.setOnRefreshListener(this);

        mAdapter = new WeCahtAdapter(R.layout.item_weixin);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
           LogUtil.d(TAG, "item "+ position+" clicked!");
        });
    }

    @Override
    public void showWeCahtData(WeixinBean result) {
        if (mSwipeLayout != null && mSwipeLayout.isRefreshing()) {
            mSwipeLayout.setRefreshing(false);
            mAdapter.setEnableLoadMore(true);
        }

        if (mAdapter != null && mAdapter.isLoading()) {
            mSwipeLayout.setEnabled(true);
        }


        if (mPageIndex == 1) {
            //刷新
            mAdapter.setNewData(result.getNewslist());
            LogUtil.d(TAG, "刷新成功");
            if (isDataRefresh) {
                showMsgTip("刷新成功");
                isDataRefresh = false;
            }
        } else {
            //加载更多
            mAdapter.addData(result.getNewslist());
        }
        if (result.getNewslist().size() == PAGE_SIZE) {
            mAdapter.loadMoreComplete();
        } else if (result.getNewslist().size() < PAGE_SIZE) {
            mAdapter.loadMoreEnd();
        }
    }

    @Override
    public void getDataFail(String msg) {
        mAdapter.loadMoreFail();
        mSwipeLayout.setEnabled(true);
        mSwipeLayout.setRefreshing(false);
//        showErrorTip(msg);
        showErrorView(R.layout.empty_view);
    }


    @Override
    public void onRefresh() {
        // TODO(ZFY): 2018/3/1 swipe
        mPageIndex = 1;
        mPresenter.getWeChatData(PAGE_SIZE, mPageIndex);
        isDataRefresh = true;
        //防止下拉刷新的时候还可以上拉加载
        mAdapter.setEnableLoadMore(false);
    }

    @Override
    public void onLoadMoreRequested() {
        // TODO(ZFY): 2018/3/1 quickAdapter
        mPageIndex++;
        mPresenter.getWeChatData(PAGE_SIZE, mPageIndex);
        //防止上拉加载的时候还可以刷新
        mSwipeLayout.setEnabled(false);
    }
}
