package com.example.test_61;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        webView = (WebView)findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://m.weather.com.cn/");
        webView.setInitialScale(57*4);

        Button bj = (Button)findViewById(R.id.bj);
        bj.setOnClickListener(this);
        Button sh = (Button)findViewById(R.id.sh);
        sh.setOnClickListener(this);
        Button heb = (Button)findViewById(R.id.heb);
        heb.setOnClickListener(this);
        Button cc = (Button)findViewById(R.id.cc);
        cc.setOnClickListener(this);
        Button sy = (Button)findViewById(R.id.sy);
        sy.setOnClickListener(this);
        Button gz = (Button)findViewById(R.id.gz);
        gz.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bj) {
            openUrl("101010100");
        } else if (id == R.id.sh) {
            openUrl("101020100");
        } else if (id == R.id.heb) {
            openUrl("101050101");
        } else if (id == R.id.cc) {
            openUrl("101060101");
        } else if (id == R.id.sy) {
            openUrl("101070101");
        } else if (id == R.id.gz) {
            openUrl("101280101");
        }
    }

    private void openUrl(String id) {
        webView.loadUrl("http://m.weather.com.cn/mweather/"+id+".shtml");
    }
}