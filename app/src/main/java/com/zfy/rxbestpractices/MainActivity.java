package com.zfy.rxbestpractices;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.zfy.rxbestpractices.base.BaseMVPActivity;
import com.zfy.rxbestpractices.config.App;
import com.zfy.rxbestpractices.contract.MainContract;
import com.zfy.rxbestpractices.di.component.DaggerMainActivityComponent;
import com.zfy.rxbestpractices.di.module.MainActivityModule;
import com.zfy.rxbestpractices.presenter.MainPersenter;
import com.zfy.rxbestpractices.util.ActivityUtil;
import com.zfy.rxbestpractices.util.LogUtil;
import com.zfy.rxbestpractices.util.SnackBarUtil;
import com.zfy.rxbestpractices.weixin.WeChatFragment;

import butterknife.BindView;

/**
 * @author: fanyuzeng on 2018/2/28 14:20
 */
public class MainActivity extends BaseMVPActivity<MainPersenter> implements MainContract.View {

    @BindView(R.id.materialViewPager)
    MaterialViewPager mViewPager;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mDrawerView;
    
    private boolean mAppExit = false;


    @Override
    protected void initViews() {

        //access permission
        mPresenter.checkPermissions();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void showPermissionFailDialog() {
        LogUtil.d(TAG, "showPermissionFailDialog");
        showErrorTip("未申请到权限，请重新授权");
    }

    @Override
    public void getPermissionSuccess() {
        LogUtil.d(TAG, "getPermissionSuccess");
        initViewImpl();
    }


    @Override
    protected void inject() {
        DaggerMainActivityComponent
                .builder()
                .appComponent(App.getAppComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build().inject(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViewImpl() {
        //ToolBar
        final Toolbar toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.mipmap.icon_menu);
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
            ab.setDisplayShowTitleEnabled(true);
            ab.setDisplayUseLogoEnabled(false);
            ab.setHomeButtonEnabled(true);
            setTitle("");
        }

        //DrawerVoiew
        mDrawerView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.github_navigation_menu_item:
                    //ignore
                    break;
                case R.id.about_me_navigation_menu_item:
                    AboutMeActivity.launchActivity(MainActivity.this);
                    break;
                case R.id.thanks_navigation_menu_item:
                    ThanksActivity.launchActivity(MainActivity.this);
                    break;
                case R.id.setting_navigation_menu_item:
                    SettingActivity.launchActivity(MainActivity.this);
                    break;
                default:
                    break;
            }
            mDrawerLayout.closeDrawers();
            return true;
        });

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position % 4) {
                    case 0:
                        return WeChatFragment.newInstance();
                    //case 1:
                    //    return RecyclerViewFragment.newInstance();
                    //case 2:
                    //    return WebViewFragment.newInstance();
                    default:
                        return RecyclerViewFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 4) {
                    case 0:
                        return getString(R.string.wechat_article);
                    case 1:
                        return getString(R.string.gank_article);
                    case 2:
                        return getString(R.string.look_video);
                    case 3:
                        return getString(R.string.article_pre_day);
                    default:
                        break;
                }
                return "";
            }
        });

        mViewPager.setMaterialViewPagerListener(page -> {
            switch (page) {
                case 0:
                    return HeaderDesign.fromColorResAndUrl(
                            R.color.green,
                            getResString(R.string.header_image_url1));
                case 1:
                    return HeaderDesign.fromColorResAndUrl(
                            R.color.blue,
                            getResString(R.string.header_image_url2));
                case 2:
                    return HeaderDesign.fromColorResAndUrl(
                            R.color.cyan,
                            getResString(R.string.header_image_url3));
                case 3:
                    return HeaderDesign.fromColorResAndUrl(
                            R.color.red,
                            getResString(R.string.header_image_url4));
                default:
                    break;
            }
            //execute others actions if needed (ex : modify your header logo)
            return null;
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
    }

    public static void launchActivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    public String getResString(int stringRes) {
        return App.getInstance().getResources().getString(stringRes);
    }


    @Override
    public void onBackPressed() {
        LogUtil.d(TAG, "mAppExit=" + mAppExit);
        if (!mAppExit) {
            mAppExit = true;
            SnackBarUtil.shortSnackbar(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), "再按一次，退出程序", SnackBarUtil.ERROR).show();
            mHandler.postDelayed(() -> mAppExit = false, 2000);
        } else {
            ActivityUtil.getInstance().finishActivityAll();
            //创建ACTION_MAIN
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //启动ACTION_MAIN，直接回到桌面
            this.startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
