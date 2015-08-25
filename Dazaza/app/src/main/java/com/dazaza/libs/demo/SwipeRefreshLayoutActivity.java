package com.dazaza.libs.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.dazaza.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * support v4
 * Created by cunqingli on 2015/8/25.
 */
public class SwipeRefreshLayoutActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = SwipeRefreshLayoutActivity.class.getSimpleName();

    @Bind(R.id.refreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_demo_swipe_refresh_layout);
        ButterKnife.bind(this);

        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setOnRefreshListener(this);
            // change color every cycle
            swipeRefreshLayout.setColorSchemeResources(R.color.loading_color_1, R.color.loading_color_2, R.color.loading_color_3);
        }
    }

    @Override
    public void onRefresh() {
        log("->onRefresh()");

        // stop loading after some seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        }, 5000);
    }

    private void log(String msg) {
        Log.i(TAG, msg);
    }
}
