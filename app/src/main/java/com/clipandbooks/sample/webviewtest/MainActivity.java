package com.clipandbooks.sample.webviewtest;

import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    WebView webpage;
    Button btn1;
    String URL = "http://beta.html5test.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webpage = (WebView)findViewById(R.id.webview1);
        btn1 = (Button)findViewById(R.id.case1);
        btn1.setOnClickListener(this);

        webpage.setWebViewClient(new CustomWebViewClient());
        webpage.setWebChromeClient(new CustomWebChromeClient());

        WebSettings webSettings = webpage.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setTextZoom(100);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowFileAccessFromFileURLs(true);
            webSettings.setAllowUniversalAccessFromFileURLs(true);
        }

        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDefaultTextEncodingName("euc-kr");

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.case1 :
                webpage.loadUrl(URL);
                break;
        }
    }


    class CustomWebViewClient extends WebViewClient {

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//             webView 로 감싼 페이지에서는 전화걸기가 되지 않음,  WebViewClient의 shouldOverrideUrlLoading 에서 구현 필요
//            if (url.startsWith("tel:")) {
//                return true;
//            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    class CustomWebChromeClient extends WebChromeClient {

        @Override
        public boolean onConsoleMessage(ConsoleMessage cm) {
            return true;
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            call("onAlertPopup", null);
            return super.onJsAlert(view, url, message, result);
        }

    }


    public void call(String command, String str) {
        return;
    }


}
