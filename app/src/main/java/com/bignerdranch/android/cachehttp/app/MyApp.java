package com.bignerdranch.android.cachehttp.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/8/7/007.
 */

public class MyApp extends Application {
    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }
}
