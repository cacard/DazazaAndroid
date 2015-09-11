package com.dazaza.system;

import android.app.Application;
import android.os.Environment;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by cunqingli on 2015/8/25.
 */
public class MyApplication extends Application {

    private static MyApplication instance;
    public static String storageExternalPath = "";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        initPath();
        Fresco.initialize(this);

        NetworkStatReceiver.checkNetworkState();
    }

    private void initPath() {
        storageExternalPath = Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
