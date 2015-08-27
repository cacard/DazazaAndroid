package com.dazaza.ui;

import android.os.Bundle;
import android.view.View;

import com.dazaza.R;
import com.dazaza.ui.view.TitleBarView;

/**
 * Created by cunqingli on 2015/8/25.
 */
public class SettingActivity extends BaseActivity {

    private TitleBarView titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_setting);
        initView();
        initListener();
    }

    private void initView() {
        titleBar = (TitleBarView) findViewById(R.id.titleBar);
    }

    private void initListener() {
        if (titleBar != null && titleBar.getClose() != null) {
            titleBar.getClose().setVisibility(View.VISIBLE);
            titleBar.getClose().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quite();
                }
            });
        }
    }
}
