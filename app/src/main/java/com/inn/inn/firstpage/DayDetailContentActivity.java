package com.inn.inn.firstpage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.inn.inn.R;
import com.inn.inn.common.InnBaseActivity;
import com.inn.inn.customview.TopBarView;

public class DayDetailContentActivity extends InnBaseActivity {

    private static final String INTENT_KEY_CONTENT_TITLE = "INTENT_KEY_CONTENT_TITLE";
    private static final String INTENT_KEY_CONTENT_URL = "INTENT_KEY_CONTENT_URL";

    private WebView webView;
    private TopBarView topBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_detail_content);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String title = intent.getStringExtra(INTENT_KEY_CONTENT_TITLE);
        String contentUrl = intent.getStringExtra(INTENT_KEY_CONTENT_URL);
        webView.loadUrl(contentUrl);
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.day_detail_content_web_view);
        topBarView = (TopBarView) findViewById(R.id.day_detail_content_top_bar);
        topBarView.setTopBarTitle("推荐详情");
        topBarView.setTopBarLeftVisibility(true);
        topBarView.setOnTopBarClickListener(new TopBarView.TopBarClickListener() {
            @Override
            public void leftClickListener() {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
            }

            @Override
            public void rightClickListener() {

            }
        });
        initWebView();
    }

    private void initWebView() {
        WebSettings webSettings = webView.getSettings();
        webView.requestFocusFromTouch();
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        webSettings.setJavaScriptEnabled(true);  //支持js
        webSettings.setPluginState(WebSettings.PluginState.ON);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true);  //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setSupportZoom(true);  //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。
        //若上面是false，则该WebView不可缩放，这个不管设置什么都不能缩放。
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        webSettings.supportMultipleWindows();  //多窗口
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //关闭webview中缓存
        webSettings.setAllowFileAccess(true);  //设置可以访问文件
        webSettings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true);  //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    public static void startDayDetailContentActivity(Context context, String title, String contentUrl) {
        Intent intent = new Intent(context, DayDetailContentActivity.class);
        intent.putExtra(INTENT_KEY_CONTENT_TITLE, title);
        intent.putExtra(INTENT_KEY_CONTENT_URL, contentUrl);
        context.startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
