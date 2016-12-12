package com.android.lala.market.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.lala.R;
import com.android.lala.base.BaseActivity;
import com.android.lala.utils.LalaLog;

import cn.appsdream.nestrefresh.util.L;

/**
 * Created by ZH on 2016/11/22.
 * 497239511@qq.com
 */
public class WebBuyActivity extends BaseActivity{
    private WebView webView;
    private String link;
    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_webbuy);
        link=getIntent().getStringExtra("links");
        LalaLog.i("web",link);
        webView=findView(R.id.buyweb);
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(link);
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode==KeyEvent.KEYCODE_BACK)
//        {
//            if(webView.canGoBack())
//            {
//                webView.goBack();//返回上一页面
//                return true;
//            }
//            else
//            {
//                finish();
//            }
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
