package com.dazaza.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.dazaza.R;

/**
 * Created by cunqingli on 2015/8/27.
 */
public class MenuBottomView extends FrameLayout {

    private Context context;

    public MenuBottomView(Context context) {
        super(context);
        initView(context);
    }

    public MenuBottomView(Context context, AttributeSet attrs) {
        super(context, attrs);initView(context);
    }

    public MenuBottomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.view_menu_bottom, this, true);
    }

    public ImageView getBackButton() {
        return  (ImageView)this.findViewById(R.id.imgBack);
    }
}
