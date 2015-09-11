package com.dazaza.system;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by cunqingli on 2015/8/28.
 */
public class NetworkStatReceiver extends BroadcastReceiver {

    private static final String TAG = "NetworkStatReceiver";
    public static final String ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    public static final int NETWORK_TYPE_UNKONWN = -1;
    public static final int NETWORK_TYPE_WIFI = 1;
    public static final int NETWORK_TYPE_MOBILE = 2;

    private WeakReference<WifiStatChangeCallback> ref;
    private static boolean isNetworkAvailable = false;
    private static int networkType = -1;

    public NetworkStatReceiver(WifiStatChangeCallback callback) {
        if (callback != null) {
            ref = new WeakReference<WifiStatChangeCallback>(callback);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        log("->onReceive()");

        checkNetworkState();

        if (ref != null && ref.get() != null) {
            ref.get().networkStateChanged(isNetworkAvailable, networkType);
        }
    }

    public static void checkNetworkState() {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileNetInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo allNetInfo = cm.getActiveNetworkInfo();

        if (allNetInfo != null) {
            if (allNetInfo.isAvailable() && (allNetInfo.isConnected() || allNetInfo.isConnectedOrConnecting())) {
                isNetworkAvailable = true;
            }
        }

        if (mobileNetInfo != null) {
            if (mobileNetInfo.isAvailable() && (mobileNetInfo.isConnected() || mobileNetInfo.isConnectedOrConnecting())) {
                isNetworkAvailable = true;
                networkType = NETWORK_TYPE_MOBILE;
            }
        }

        if (wifiNetInfo != null) {
            if (wifiNetInfo.isAvailable() && ( wifiNetInfo.isConnected() || wifiNetInfo.isConnectedOrConnecting())) {
                isNetworkAvailable = true;
                networkType = NETWORK_TYPE_WIFI;
            }
        }
    }

    public static int getNetworkType() {
        return networkType;
    }

    public static boolean isNetworkAvailable() {
        return isNetworkAvailable;
    }

    public static interface WifiStatChangeCallback {
        void networkStateChanged(boolean isAvialble, int type);
    }

    private void log(String msg) {
        Log.i(TAG, msg);
    }
}
