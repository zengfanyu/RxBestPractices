package com.zfy.rxbestpractices.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.zfy.rxbestpractices.R;

/**
 * @author: fanyuzeng on 2018/3/14 19:04
 */
public class AutoRatioImageView extends android.support.v7.widget.AppCompatImageView {
    /**
     * 宽高比
     */
    private float ratio;

    public AutoRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoRatioImageView);
        ratio = typedArray.getFloat(R.styleable.AutoRatioImageView_ratio, 0.0f);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取宽度的模式和尺寸
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        //获取高度的模式和尺寸
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //宽确定，高不确定
        if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY && ratio != 0) {
            //根据宽度和比例计算高度
            heightSize = (int) (widthSize * ratio + 0.5f);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY);
        } else if (widthMode != MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY & ratio != 0) {
            widthSize = (int) (heightSize / ratio + 0.5f);
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.EXACTLY);
        } else if (widthMode != MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY && ratio != 0) {
            //这种情况是当宽高都是 wrap_content 时，那就按照 宽确定，高wrap_content 来计算
            heightSize = (int) (widthSize * ratio + 0.5f);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY);
        }
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 设置宽高比
     *
     * @param ratio
     */
    public void setRatio(float ratio) {
        this.ratio = ratio;
    }
}
