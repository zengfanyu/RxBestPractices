package com.zfy.rxbestpractices;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author: fanyuzeng on 2018/2/28 16:41
 */
public class ThanksActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);
    }
    public static void launchActivity(Activity activity) {
        Intent intent = new Intent(activity, ThanksActivity.class);
        activity.startActivity(intent);
    }
}
