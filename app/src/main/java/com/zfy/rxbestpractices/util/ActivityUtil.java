package com.zfy.rxbestpractices.util;

import android.support.annotation.NonNull;

import com.zfy.rxbestpractices.base.BaseActivity;

import java.util.Stack;

/**
 * 管理Activity的工具
 *
 * @author: fanyuzeng on 2018/3/1 10:33
 */
public class ActivityUtil {
    private static final String TAG = "==ActivityUtil==";

    private static volatile ActivityUtil sInstance;

    private Stack<BaseActivity> mActivityStack = new Stack<>();

    private ActivityUtil() {
    }

    public static ActivityUtil getInstance() {
        if (sInstance == null) {
            synchronized (ActivityUtil.class) {
                if (sInstance == null) {
                    sInstance = new ActivityUtil();
                }
            }
        }
        return sInstance;
    }

    /**
     * Activity入栈
     *
     * @param activity
     */
    public void pushActivity(@NonNull BaseActivity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<>();
        }
        LogUtil.i(TAG, "pushActivity " + activity.getClass().getName());
        mActivityStack.push(activity);
    }

    /**
     * Activity出栈
     *
     * @param activity
     */
    public void removeActivity(@NonNull BaseActivity activity) {
        LogUtil.i(TAG, "removeActivity " + activity.getClass().getName());
        if (activity.isFinishing()) {
            mActivityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishActivityAll() {
        if (mActivityStack != null && mActivityStack.size() > 0) {
            Stack<BaseActivity> stack = new Stack<>();
            for (BaseActivity activity : mActivityStack) {
                stack.add(activity);
                activity.finish();
            }
            mActivityStack.removeAll(stack);
            LogUtil.i(TAG, "finish all activity down");
            System.gc();
            System.exit(0);
        }
    }
}
