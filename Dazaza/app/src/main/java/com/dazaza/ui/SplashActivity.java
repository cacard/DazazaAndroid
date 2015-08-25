package com.dazaza.ui;

import android.content.Intent;
import android.os.Bundle;

import com.dazaza.libs.demo.CircleImageViewActivity;

/**
 * Created by cunqingli on 2015/8/25.
 */
public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.startActivity(new Intent(this, CircleImageViewActivity.class));
    }
}
