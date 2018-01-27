package com.huangjie.driver.ui.webview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.huangjie.driver.R;
import com.huangjie.driver.constant.Constant;
import com.huangjie.driver.databinding.ActivityWebviewBinding;
import com.huangjie.driver.utils.Utils;

/**
 * Created by huangjie on 2017/6/4.
 */

public class WebViewActivity extends AppCompatActivity {
    public static final String SEND_TITle = "title";
    private ActivityWebviewBinding mBindingView;
    private WebChromeClient mWebChromeClient;
    private String mUrl;
    private String mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBindingView = DataBindingUtil.setContentView(this, R.layout.activity_webview);
        mUrl = getIntent().getStringExtra(Constant.SEND_DATA);
        mTitle = getIntent().getStringExtra(SEND_TITle);
        setWebView();
        init();
    }

    /**
     * webview设置
     */
    private void setWebView() {
        WebSettings webseting = mBindingView.webviewWebview.getSettings();
        webseting.setSupportZoom(true);
        webseting.setLoadWithOverviewMode(false);
        webseting.setSaveFormData(true);
        webseting.setBuiltInZoomControls(true);
        webseting.setDisplayZoomControls(false);
        // 启动应用缓存
        webseting.setAppCacheEnabled(true);
        // 设置缓存模式
        webseting.setCacheMode(WebSettings.LOAD_DEFAULT);
        // setDefaultZoom  api19被弃用
        // 设置此属性，可任意比例缩放。
        webseting.setUseWideViewPort(true);
        // 缩放比例 1
        mBindingView.webviewWebview.setInitialScale(1);
        // 告诉WebView启用JavaScript执行。默认的是false。
        webseting.setJavaScriptEnabled(true);
        mWebChromeClient = new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mBindingView.webviewProgressbar.setProgress(newProgress);
                if (newProgress == 100) {
                    mBindingView.webviewProgressbar.setVisibility(View.GONE);
                }
            }
        };
        mBindingView.webviewWebview.setWebChromeClient(mWebChromeClient);
        mBindingView.webviewWebview.setWebViewClient(new WebViewClient());

    }

    private void init() {
        setSupportActionBar(mBindingView.webviewToolbar);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayShowTitleEnabled(false);
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(Utils.getDrawables(R.drawable.icon_back));
        }
        mBindingView.webviewToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mBindingView.webviewWebview.loadUrl(mUrl);
        mBindingView.webviewToolbar.setTitle(mTitle);

    }
}
