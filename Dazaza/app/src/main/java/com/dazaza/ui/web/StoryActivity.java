package com.dazaza.ui.web;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.dazaza.R;
import com.dazaza.config.Constants;
import com.dazaza.model.ModelStory;
import com.dazaza.ui.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cunqingli on 2015/8/25.
 */
public class StoryActivity extends BaseActivity {

    private static final String TAG = StoryActivity.class.getSimpleName();

    @Bind(R.id.webView)
    WebView webView;

    private ModelStory modelStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_story);
        ButterKnife.bind(this);
        initWebView();
        getIntentData();
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
        if (this.getIntent().hasExtra(Constants.KEY_MODEL_STORY) ) {
            modelStory = this.getIntent().getParcelableExtra(Constants.KEY_MODEL_STORY);
        }
    }

    private void log(String msg) {
        Log.i(TAG, msg);
    }
}
