package com.dazaza.ui.web;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.dazaza.R;
import com.dazaza.config.Constants;
import com.dazaza.html.HtmlBuilder;
import com.dazaza.model.ModelStory;
import com.dazaza.ui.BaseActivity;
import com.dazaza.ui.view.MenuBottomView;
import com.dazaza.ui.view.RotateLoading;

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
    @Bind(R.id.loading)
    RotateLoading loading;

    private ImageView imgBack;
    private ModelStory modelStory;

    private static final int MODE_URL = 1; // 加载远程url
    private static final int MODE_LOCAL = 2; // 加载本地合并成的HTML
    private int mode = MODE_LOCAL; // 模式

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_story);
        ButterKnife.bind(this);
        initView();
        initWebView();
        initListener();
        getIntentData();

        if (mode == MODE_URL) {
            startLoadingUrl();
        }
        if (mode == MODE_LOCAL) {
            startLoadingLocal();
        }
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
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
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
                switch2Loading();
                webView.loadUrl(url);
            }
        }
    }

    private void startLoadingLocal() {
        if (modelStory == null) {
            // show empty
        }

        if (webView != null) {
            switch2Loading();
            webView.loadDataWithBaseURL("file:///android_asset/", HtmlBuilder.buildHtml(modelStory), "text/html", "UTF-8", null);
        }
    }

    public void switch2Loading() {
        if (webView != null) {
            webView.setVisibility(View.GONE);
        }
        if (loading != null) {
            loading.setVisibility(View.VISIBLE);
            loading.start();
        }
    }

    public void switch2Webview() {
        if (webView != null) {
            webView.setVisibility(View.VISIBLE);
        }
        if (loading != null) {
            loading.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loading.stop();
                    loading.setVisibility(View.GONE);
                }
            }, 600);
        }
    }

    private void log(String msg) {
        Log.i(TAG, msg);
    }
}
