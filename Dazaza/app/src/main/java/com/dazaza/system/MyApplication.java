package com.dazaza.system;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by cunqingli on 2015/8/25.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
