package com.zfy.rxbestpractices.weixin;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.zfy.rxbestpractices.R;
import com.zfy.rxbestpractices.base.BaseMVPFragment;
import com.zfy.rxbestpractices.config.App;
import com.zfy.rxbestpractices.contract.WeixinContract;
import com.zfy.rxbestpractices.di.component.DaggerWeixinFragmentComponent;
import com.zfy.rxbestpractices.di.module.WeixinFragmentModule;
import com.zfy.rxbestpractices.http.bean.WeixinBean;
import com.zfy.rxbestpractices.presenter.WeixinPresenter;
import com.zfy.rxbestpractices.util.LogUtil;
import com.zfy.rxbestpractices.util.SnackBarUtil;

import butterknife.BindView;

/**
 * @author: fanyuzeng on 2018/3/1 15:35
 */
public class WeixinFragment extends BaseMVPFragment<WeixinPresenter> implements WeixinContract.View,
        SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    private static final String TAG = "==WeixinFragment==";
    @BindView(R.id.id_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.id_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeLayout;

    private int mPageIndex = 1;
    private static final int PAGE_SIZE = 16;

    private WeixinAdapter mAdapter;

    public static WeixinFragment newInstance() {
        return new WeixinFragment();
    }

    @Override
    protected void initData() {
        mPresenter.getWeixinData(PAGE_SIZE, mPageIndex);
    }

    @Override
    protected int getContentLayoutRes() {
        return R.layout.fragment_weixin;
    }

    @Override
    protected void initInject() {
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

        mAdapter = new WeixinAdapter(R.layout.item_weixin);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            SnackBarUtil.ShortSnackbar(mRecyclerView, "item " + position + " clicked", SnackBarUtil.INFO);
        });
    }

    @Override
    public void showWeixinData(WeixinBean result) {
        if (mSwipeLayout != null && mSwipeLayout.isRefreshing()) {
            mSwipeLayout.setRefreshing(false);
            mAdapter.setEnableLoadMore(true);
        }

        if (mAdapter != null && mAdapter.isLoading()) {
            mSwipeLayout.setEnabled(true);
        }

        if (mPageIndex == 1) {
            mAdapter.setNewData(result.getNewslist());
            LogUtil.d(TAG,"刷新成功" );
            showMsg("刷新成功");
        } else {
            mAdapter.addData(result.getNewslist());
        }
        if (result.getNewslist().size() == PAGE_SIZE) {
            mAdapter.loadMoreComplete();
        } else if (result.getNewslist().size() < PAGE_SIZE) {
            mAdapter.loadMoreEnd();
        }
    }

    @Override
    public void getDataFail() {
        mAdapter.loadMoreFail();
        mSwipeLayout.setEnabled(true);
        mSwipeLayout.setRefreshing(false);
        showError("load failed");
        showEmptyView(R.layout.empty_view);
    }


    @Override
    public void onRefresh() {
        // TODO(ZFY): 2018/3/1 swipe
        mPageIndex = 1;
        mPresenter.getWeixinData(PAGE_SIZE, mPageIndex);
        //防止下拉刷新的时候还可以上拉加载
        mAdapter.setEnableLoadMore(false);
    }

    @Override
    public void onLoadMoreRequested() {
        // TODO(ZFY): 2018/3/1 quickAdapter
        mPageIndex++;
        mPresenter.getWeixinData(PAGE_SIZE, mPageIndex);
        //防止上拉加载的时候还可以刷新
        mSwipeLayout.setEnabled(false);
    }
}
