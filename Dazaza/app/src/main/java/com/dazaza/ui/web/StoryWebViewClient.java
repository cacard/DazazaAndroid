package com.dazaza.ui.web;

import android.graphics.Bitmap;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.lang.ref.WeakReference;

/**
 * Created by cunqingli on 2015/8/26.
 */
public class StoryWebViewClient extends WebViewClient {

    private WeakReference<StoryActivity> ref4Activity;

    public StoryWebViewClient(StoryActivity activity) {
        super();
        if (activity != null) {
            ref4Activity = new WeakReference<StoryActivity>(activity);
        }
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

        if (ref4Activity == null) {
            return;
        }

        StoryActivity activity = ref4Activity.get();
        if (activity == null) {
            return;
        }

        activity.switch2Webview();
    }

}
