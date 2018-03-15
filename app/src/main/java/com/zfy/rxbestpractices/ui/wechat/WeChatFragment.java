package com.zfy.rxbestpractices.ui.wechat;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.zfy.rxbestpractices.R;
import com.zfy.rxbestpractices.base.BaseMVPFragment;
import com.zfy.rxbestpractices.config.App;
import com.zfy.rxbestpractices.config.Constants;
import com.zfy.rxbestpractices.contract.WeChatContract;
import com.zfy.rxbestpractices.di.component.DaggerWeixinFragmentComponent;
import com.zfy.rxbestpractices.di.module.WeixinFragmentModule;
import com.zfy.rxbestpractices.http.bean.WeChatBean;
import com.zfy.rxbestpractices.presenter.WeChatPresenter;
import com.zfy.rxbestpractices.ui.web.WebActivity;
import com.zfy.rxbestpractices.util.LogUtil;

import java.util.List;

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
    private static final int PAGE_SIZE = 8;

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

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void showWeCahtData(WeChatBean result) {
        LogUtil.d(TAG, "showWeCahtData");
        List<WeChatBean.NewslistBean> datas = result.getNewslist();

        if (mPageIndex == 1) {
            datas.get(0).setItemType(WeChatBean.NewslistBean.ITEM_TYPE_BIG);
            if (mAdapter == null) {
                mAdapter = new WeCahtAdapter(datas);
                mAdapter.setOnLoadMoreListener(this, mRecyclerView);
                mAdapter.setOnItemClickListener((adapter, view, position) -> {
                    LogUtil.d(TAG, "item " + position + " clicked!");
                    WeChatBean.NewslistBean newslistBean = (WeChatBean.NewslistBean) adapter.getData().get(position);
                    WebActivity.launchActivity(mContext,
                            new WebActivity.Builder()
                                    .setDataType(Constants.TYPE_WECHAT)
                                    .setGuid(newslistBean.getUrl())
                                    .setImgUrl(newslistBean.getPicUrl())
                                    .setShowLikeIcon(true)
                                    .setTitle(newslistBean.getTitle())
                                    .setUrl(newslistBean.getUrl()));
                });
                mRecyclerView.setAdapter(mAdapter);
            }
            //刷新
            mAdapter.setNewData(datas);
            LogUtil.d(TAG, "刷新成功");
            if (isDataRefresh) {
                showMsgTip("刷新成功");
                isDataRefresh = false;
            }
        } else {
            //加载更多
            mAdapter.addData(datas);
        }

        if (mSwipeLayout != null && mSwipeLayout.isRefreshing()) {
            mSwipeLayout.setRefreshing(false);
            mAdapter.setEnableLoadMore(true);
        }

        if (mAdapter != null && mAdapter.isLoading()) {
            mSwipeLayout.setEnabled(true);
        }

        if (datas.size() == PAGE_SIZE) {
            mAdapter.loadMoreComplete();
        } else if (datas.size() < PAGE_SIZE) {
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
        LogUtil.d(TAG, "onLoadMoreRequested pageIndex=" + mPageIndex);
        // TODO(ZFY): 2018/3/1 quickAdapter
        mPageIndex++;
        mPresenter.getWeChatData(PAGE_SIZE, mPageIndex);
        //防止上拉加载的时候还可以刷新
        mSwipeLayout.setEnabled(false);
    }
}
