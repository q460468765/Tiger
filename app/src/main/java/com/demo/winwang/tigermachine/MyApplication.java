package com.demo.winwang.tigermachine;

import android.app.Application;

import com.demo.winwang.tigermachine.utils.ScreenFitHelper;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new ScreenFitHelper(this, 750).activate();//激活屏幕适配方案
    }
}
