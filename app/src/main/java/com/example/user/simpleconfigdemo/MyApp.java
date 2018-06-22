package com.example.user.simpleconfigdemo;

import android.app.Application;

/**
 * Created by lele on 2018/6/21.
 */

public class MyApp extends Application {
    public static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
