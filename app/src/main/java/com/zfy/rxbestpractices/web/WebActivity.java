package com.zfy.rxbestpractices.web;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.zfy.rxbestpractices.R;
import com.zfy.rxbestpractices.base.BaseActivity;
import com.zfy.rxbestpractices.config.App;
import com.zfy.rxbestpractices.db.GreenDaoManager;
import com.zfy.rxbestpractices.db.bean.LikeBean;
import com.zfy.rxbestpractices.util.LogUtil;
import com.zfy.rxbestpractices.util.SnackBarUtil;
import com.zfy.rxbestpractices.widget.X5WebView;

import java.io.Serializable;

import butterknife.BindView;

/**
 * @author: fanyuzeng on 2018/3/5 11:24
 */
public class WebActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "-WebActivity-";
    public static final String DATA_BEAN = "data_bean";

    @BindView(R.id.id_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeLayout;
    @BindView(R.id.id_web_view)
    X5WebView mWebView;
    @BindView(R.id.id_toolbar)
    Toolbar mToolbar;
    private int mDataType;
    private String mGuid;
    private String mImgUrl;
    private boolean mShowLikeIcon;
    private String mTitle;
    private String mUrl;
    private MenuItem mMenuLikeItem;
    private boolean mLike = false;
    private GreenDaoManager mDaoManager;

    @Override
    protected void initViews() {

        LogUtil.d(TAG, "initViews");

        mDaoManager = App.getAppComponent().getGreenDaoManager();
        //ToolBar
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mSwipeLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeLayout.setOnRefreshListener(this);

        initDatas();
    }

    private void initDatas() {
        LogUtil.d(TAG, "initData");
        Builder data = (Builder) getIntent().getSerializableExtra(DATA_BEAN);

        LogUtil.d(TAG, data.toString());

        mDataType = data.dataType;
        mGuid = data.guid;
        mImgUrl = data.imgUrl;
        mShowLikeIcon = data.showLikeIcon;
        mTitle = data.title;
        mUrl = data.url;

        setTitle(mTitle);

        initWebView();
    }

    private void initWebView() {
        if (mWebView == null) {
            return;
        }
        LogUtil.d(TAG, "initWebView");

        WebSettings settings = mWebView.getSettings();
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        settings.setGeolocationEnabled(true);
        settings.setGeolocationDatabasePath(dir);
        // TODO(ZFY): 2018/3/5 此处判断是否夜间模式
        mWebView.setDayOrNight(true);

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                return super.shouldOverrideUrlLoading(webView, s);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                LogUtil.d(TAG, "onPageStarted url=" + url);
                mSwipeLayout.setRefreshing(true);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                LogUtil.d(TAG, "onPageFinished url=" + url);
                mSwipeLayout.setRefreshing(false);
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, com.tencent.smtt.export.external.interfaces.SslError sslError) {
                sslErrorHandler.proceed();
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            //扩展支持alert事件
            @Override
            public boolean onJsAlert(WebView webView, String url, String message, final JsResult jsResult) {
                return true;
            }

            @Override
            public void onGeolocationPermissionsHidePrompt() {
                super.onGeolocationPermissionsHidePrompt();
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(final String origin, final GeolocationPermissionsCallback callback) {

            }
        });

        mWebView.setOnScrollChangeListener((l, t, oldl, oldt) -> {
//            LogUtil.d(TAG, "WebView onScroll t=" + t);
            if (t == 0) {
                //WebView 在顶部，此时允许 swipe 刷新
                mSwipeLayout.setEnabled(true);
            } else {
                //WebView 不在在顶部，此时不允许 swipe 刷新
                mSwipeLayout.setEnabled(false);
            }
        });
        //点击返回
        mWebView.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            }
            return false;
        });
        mWebView.loadUrl(mUrl);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_web;
    }


    @Override
    public void onRefresh() {
        mWebView.reload();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mShowLikeIcon) {
            getMenuInflater().inflate(R.menu.menu_web_with_like, menu);
            mMenuLikeItem = menu.findItem(R.id.menu_item_like);
            showLikeIconState(mDaoManager.queryByGid(mGuid));
        } else {
            getMenuInflater().inflate(R.menu.menu_web_without_like, menu);
        }
        return true;
    }

    private void showLikeIconState(boolean hasLiked) {
        if (hasLiked) {
            mMenuLikeItem.setIcon(R.mipmap.icon_like_choosen);
            mLike = true;
        } else {
            mMenuLikeItem.setIcon(R.mipmap.icon_like_default);
            mLike = false;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_like:
                if (!mLike) {
                    mLike = true;
                    mMenuLikeItem.setIcon(R.mipmap.icon_like_choosen);
                    LikeBean likeBean = new LikeBean(null, mGuid, mImgUrl, mTitle, mUrl, mDataType, System.currentTimeMillis());
                    mDaoManager.insert(likeBean);
                    SnackBarUtil.shortSnackbar(mWebView,R.string.like_success,SnackBarUtil.CONFIRM).show();
                } else {
                    mLike = false;
                    mMenuLikeItem.setIcon(R.mipmap.icon_like_default);
                    mDaoManager.deleteByGid(mGuid);
                    SnackBarUtil.shortSnackbar(mWebView,R.string.remove_like_success,SnackBarUtil.CONFIRM).show();
                }
                break;
            case R.id.menu_item_copy:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                if (cm != null) {
                    cm.setPrimaryClip(ClipData.newPlainText(null, mUrl));
                    SnackBarUtil.shortSnackbar(mWebView, R.string.copy_success, SnackBarUtil.CONFIRM).show();
                }
                break;
            case R.id.menu_item_brower:
                Uri uri = Uri.parse(mUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.setWebChromeClient(null);
        mWebView.setWebViewClient(null);
        mWebView.getSettings().setJavaScriptEnabled(false);
        mWebView.clearCache(true);
        mWebView.clearHistory();
        mWebView.removeAllViews();
    }

    public static class Builder implements Serializable {
        private String guid;
        private String imgUrl;
        private String url;
        private int dataType;
        private String title;
        private boolean showLikeIcon;

        public Builder() {

        }

        public Builder setGuid(String guid) {
            this.guid = guid;
            return this;
        }

        public Builder setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setDataType(int dataType) {
            this.dataType = dataType;
            return this;

        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setShowLikeIcon(boolean showLikeIcon) {
            this.showLikeIcon = showLikeIcon;
            return this;
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "guid='" + guid + '\'' +
                    ", imgUrl='" + imgUrl + '\'' +
                    ", url='" + url + '\'' +
                    ", dataType=" + dataType +
                    ", title='" + title + '\'' +
                    ", showLikeIcon=" + showLikeIcon +
                    '}';
        }
    }

    public static void launchActivity(Context context, Builder data) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(DATA_BEAN, data);
        context.startActivity(intent);
    }


}
