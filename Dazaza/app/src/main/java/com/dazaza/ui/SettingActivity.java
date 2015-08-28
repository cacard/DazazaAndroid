package com.dazaza.ui;

import android.os.Bundle;
import android.view.View;

import com.dazaza.R;
import com.dazaza.ui.view.TitleBarView;

/**
 * Created by cunqingli on 2015/8/25.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private TitleBarView titleBar;
    private static final String TITLE = "设置";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_setting);
        initView();
        initListener();
    }

    private void initView() {
        titleBar = (TitleBarView) findViewById(R.id.titleBar);
        if (titleBar != null) {
            titleBar.getTitle().setText(TITLE);
        }
    }

    private void initListener() {
        if (titleBar != null && titleBar.getClose() != null) {
            titleBar.getClose().setVisibility(View.VISIBLE);
            titleBar.getClose().setOnClickListener(this);
        }
        if (titleBar != null && titleBar.getImgBack() != null) {
            titleBar.getImgBack().setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvClose:
            case R.id.imgBack:
                quite();
                break;
        }
    }
}
