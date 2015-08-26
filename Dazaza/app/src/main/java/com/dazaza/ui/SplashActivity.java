package com.dazaza.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.dazaza.R;
import com.dazaza.libs.demo.CircleImageViewActivity;
import com.dazaza.libs.demo.StaggeredGridActivity;
import com.dazaza.libs.demo.SwipeRefreshLayoutActivity;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;

/**
 * Created by cunqingli on 2015/8/25.
 */
public class SplashActivity extends BaseActivity {

    private Handler myHandler = new MyHandler(this);
    private static final int MSG_GO_TO_MAIN = 1;
    private static final long DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        myHandler.sendEmptyMessageDelayed(MSG_GO_TO_MAIN, DELAY);
    }

    private static class MyHandler extends Handler {

        private WeakReference<SplashActivity> ref;

        public MyHandler(SplashActivity activity) {
            ref = new WeakReference<SplashActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (ref == null) {
                return;
            }
            SplashActivity activity = ref.get();
            if (activity == null) {
                return;
            }

            switch (msg.what) {
                case MSG_GO_TO_MAIN :
                    activity.startActivity(new Intent(activity, MainActivity.class));
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myHandler != null) {
            myHandler.removeCallbacksAndMessages(null);
        }
    }
}
