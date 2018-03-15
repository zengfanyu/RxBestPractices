package com.zfy.rxbestpractices.ui.fullscreenimg;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.zfy.rxbestpractices.R;
import com.zfy.rxbestpractices.base.BaseMVPActivity;
import com.zfy.rxbestpractices.contract.FullScreenContract;
import com.zfy.rxbestpractices.di.component.DaggerFullScreenComponent;
import com.zfy.rxbestpractices.di.module.FullScreenModule;
import com.zfy.rxbestpractices.presenter.FullScreenPresenter;
import com.zfy.rxbestpractices.util.ImageLoader;
import com.zfy.rxbestpractices.util.SnackBarUtil;
import com.zfy.rxbestpractices.util.VibratorUtil;

import butterknife.BindView;

/**
 * @author: fanyuzeng on 2018/3/14 16:18
 */
public class FullScreenImageActivity extends BaseMVPActivity<FullScreenPresenter> implements FullScreenContract.View {
    public static final String IMAGE_URL = "image_url";
    public static final String TRANSIT_PIC = "picture";

    @BindView(R.id.id_photo_view)
    PhotoView mPhotoView;
    private Drawable mImageDrawable;
    private String mImgUrl;

    public static void launchActivity(Activity activity, View view, String imgUrl) {
        Intent intent = new Intent(activity, FullScreenImageActivity.class);
        intent.putExtra(FullScreenImageActivity.IMAGE_URL, imgUrl);
        ActivityOptionsCompat optionsCompat
                = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity, view, FullScreenImageActivity.TRANSIT_PIC);
        try {
            ActivityCompat.startActivity(activity, intent,
                    optionsCompat.toBundle());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            activity.startActivity(intent);
        }
    }

    @Override
    protected void inject() {
        DaggerFullScreenComponent
                .builder()
                .fullScreenModule(new FullScreenModule(this))
                .build().inject(this);
    }

    @Override
    protected void initViews() {
        mImgUrl = getIntent().getStringExtra(IMAGE_URL);
        if (!TextUtils.isEmpty(mImgUrl)) {
            ViewCompat.setTransitionName(mPhotoView, TRANSIT_PIC);
            ImageLoader.loadAll(this, mImgUrl, new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                    mPhotoView.setImageDrawable(resource);
                    mImageDrawable = resource.getCurrent();
                }
            });
        }

        mPresenter.setupPhotoAttacher();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_full_screen_img;
    }

    @Override
    public void onSetupPhotoAttacher() {
        PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(mPhotoView);
        photoViewAttacher.setOnViewTapListener((view, x, y) -> onBackPressed());
        photoViewAttacher.setOnLongClickListener(view -> {
            if (mImageDrawable == null) {
                return false;
            }
            VibratorUtil.vibrator(this, 1000);
            VibratorUtil.vibratorCancel(this);
            mPresenter.setupTagView();
            return true;
        });
    }

    @Override
    public void onShowSaveTag() {

        View view = getLayoutInflater().inflate(R.layout.dialog_save_image, null);

        AlertDialog alertDialog = new AlertDialog.Builder(FullScreenImageActivity.this)
                .setView(view)
                .create();

        TextView saveImg = view.findViewById(R.id.save_image_tv);
        saveImg.setOnClickListener(v -> {
            mPresenter.checkPermission();
            alertDialog.dismiss();
        });

        alertDialog.show();
    }


    @Override
    public void onRequestGranted() {
        mPresenter.saveImg(this, mImageDrawable, mImgUrl);
    }

    @Override
    public void onRequestNotGranted() {
        SnackBarUtil.shortSnackbar(mPhotoView, "权限不足", SnackBarUtil.ERROR).show();
    }

    @Override
    public void onSavePhotoSuccess() {
        SnackBarUtil.shortSnackbar(mPhotoView, "保存图片成功", SnackBarUtil.CONFIRM).show();
    }

    @Override
    public void onSavePhotoFail() {
        SnackBarUtil.shortSnackbar(mPhotoView, "保存图片失败", SnackBarUtil.ERROR).show();

    }
}
