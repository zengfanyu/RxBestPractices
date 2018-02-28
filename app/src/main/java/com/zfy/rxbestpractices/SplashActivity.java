package com.zfy.rxbestpractices;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * @author: fanyuzeng on 2018/2/28 11:20
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.launchActivity(SplashActivity.this);
            }
        }, 1000);
    }
}
