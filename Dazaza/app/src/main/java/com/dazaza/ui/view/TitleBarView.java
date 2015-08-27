package com.dazaza.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dazaza.R;

/**
 * Created by cunqingli on 2015/8/27.
 */
public class TitleBarView extends RelativeLayout {

    private Context context;
    private TextView tvTitle;
    private TextView tvClose;

    public TitleBarView(Context context) {
        super(context);
        initView(context);
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TitleBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.view_title_bar, this, true);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvClose = (TextView) findViewById(R.id.tvClose);
    }

    public TextView getTitle() {
        return tvTitle;
    }

    public TextView getClose() {
        return tvClose;
    }
}
