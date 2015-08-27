package com.dazaza.ui.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.dazaza.R;
import com.dazaza.ui.SettingActivity;

/**
 * Created by cunqingli on 2015/8/27.
 */
public class MenuTopView extends FrameLayout implements View.OnClickListener {

    private Context context;
    private ImageView imgSetting;

    public MenuTopView(Context context) {
        super(context);
        initView(context);
    }

    public MenuTopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MenuTopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.view_menu_top, this, true);

        findControls();
        initListeners();
    }

    private void findControls() {
        imgSetting = (ImageView) this.findViewById(R.id.imgSetting);
    }

    private void initListeners() {
        if (imgSetting != null) {
            imgSetting.setOnClickListener(this);
        }
    }

    public ImageView getImgSetting() {
        return imgSetting;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgSetting:
                context.startActivity(new Intent(context, SettingActivity.class));
                break;
        }
    }
}
