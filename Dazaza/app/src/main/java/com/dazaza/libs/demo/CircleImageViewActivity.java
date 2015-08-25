package com.dazaza.libs.demo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;

import com.dazaza.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * see:https://github.com/hdodenhof/CircleImageView
 * Created by cunqingli on 2015/8/25.
 */
public class CircleImageViewActivity extends Activity {

    @Bind(R.id.root)
    ViewGroup layoutRoot;
    @Bind(R.id.img)
    CircleImageView imgCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_demo_circle_image_view);

        ButterKnife.bind(this);
        if (layoutRoot != null) {
            layoutRoot.setBackgroundColor(Color.parseColor("#FFFF00FF"));
        }
    }
}
