package com.zfy.rxbestpractices;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: fanyuzeng on 2018/2/28 14:20
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.materialViewPager)
    MaterialViewPager mViewPager;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mDrawerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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

        mDrawerView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
            }
        });


        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position % 4) {
                    //case 0:
                    //    return RecyclerViewFragment.newInstance();
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
                        return getResString(R.string.wechat_article);
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

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
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
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void launchActivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    public String getResString(int stringRes) {
        return Common.mAppContext.getResources().getString(stringRes);
    }
}
