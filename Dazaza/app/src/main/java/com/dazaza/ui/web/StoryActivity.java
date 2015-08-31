package com.dazaza.ui.web;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.dazaza.R;
import com.dazaza.config.Constants;
import com.dazaza.model.ModelStory;
import com.dazaza.ui.BaseActivity;
import com.dazaza.ui.view.MenuBottomView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cunqingli on 2015/8/25.
 */
public class StoryActivity extends BaseActivity {

    private static final String TAG = StoryActivity.class.getSimpleName();

    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.menuBottom)
    MenuBottomView menuBottomView;
    private ImageView imgBack;

    private ModelStory modelStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_story);
        ButterKnife.bind(this);
        initView();
        initWebView();
        initListener();
        getIntentData();
        startLoadingUrl();
    }

    private void initView() {
        if (menuBottomView != null) {
            imgBack = menuBottomView.getBackButton();
        }
    }

    private void initWebView() {
        if (webView == null) {
            return;
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setBackgroundColor(0);
        webView.setWebViewClient(new StoryWebViewClient(this));
    }

    private void getIntentData() {
        if (this.getIntent() == null) {
            return;
        }
        if (this.getIntent().hasExtra(Constants.KEY_MODEL_STORY)) {
            modelStory = this.getIntent().getParcelableExtra(Constants.KEY_MODEL_STORY);
        }
    }

    private void initListener() {
        if (imgBack != null) {
            imgBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quite();
                }
            });
        }
    }

    /**
     * 开始加载拿到的url
     */
    private void startLoadingUrl() {
        if (webView != null && modelStory != null) {
            final String url = "http://www.diglog.com/story?ismobile=1&id=" + modelStory.getInfoId();
            if (url != null && url.length() > 0) {
                webView.loadUrl(url);
            }
        }
    }

    private void log(String msg) {
        Log.i(TAG, msg);
    }
}
