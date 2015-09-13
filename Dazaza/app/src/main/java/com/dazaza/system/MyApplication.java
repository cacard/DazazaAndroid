package com.dazaza.system;

import android.app.Application;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.facebook.drawee.backends.pipeline.Fresco;

import cn.trinea.android.common.util.ScreenUtils;

/**
 * Created by cunqingli on 2015/8/25.
 */
public class MyApplication extends Application {

    private static MyApplication instance;
    public static String storageExternalPath = "";
    public static int screenWidth = 0;
    public static int screenHeight = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        getScreenInfo();

        initPath();
        Fresco.initialize(this);

        NetworkStatReceiver.checkNetworkState();
    }

    private void getScreenInfo() {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            screenHeight = displayMetrics.heightPixels;
            screenWidth = displayMetrics.widthPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
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
