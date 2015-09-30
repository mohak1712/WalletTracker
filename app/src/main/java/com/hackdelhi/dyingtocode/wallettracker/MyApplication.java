package com.hackdelhi.dyingtocode.wallettracker;

import android.app.Application;
import android.content.Context;

/**
 * Created by Sarthak on 07-07-2015.
 */
public class MyApplication extends Application {
    public static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public MyApplication getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance.getApplicationContext();

    }


}