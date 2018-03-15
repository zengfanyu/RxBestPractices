package com.zfy.rxbestpractices.util;

import android.app.Activity;
import android.app.Service;
import android.os.Vibrator;

/**
 * 震动工具类
 * @author: fanyuzeng on 2018/3/15 9:50
 */
public class VibratorUtil {

    private static boolean isVibrating = false;

    public static void vibrator(Activity activity, long milliseconds) {
        Vibrator vibrator = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        if (!isVibrating) {
            isVibrating = true;
            vibrator.vibrate(milliseconds);
        }
    }

    public static void vibratorCancel(Activity activity) {
        Vibrator vibrator = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        if (isVibrating) {
            isVibrating = false;
            vibrator.cancel();
        }
    }
}
