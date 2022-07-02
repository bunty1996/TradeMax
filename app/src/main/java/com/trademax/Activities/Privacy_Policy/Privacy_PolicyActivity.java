package com.trademax.Activities.Privacy_Policy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.trademax.R;

public class Privacy_PolicyActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView webView;
    private ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLightStatusBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy__policy);
        init();
        listiners();

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://13.54.226.124:4604/privacy");

    }

    private void init() {
        img_back = findViewById(R.id.img_back);
        webView = (WebView) findViewById(R.id.webView);

    }

    private void listiners() {
        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == img_back) {
            finish();
        }
    }

    private void setLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = activity.getWindow().getDecorView().getSystemUiVisibility(); // get current flag
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;   // add LIGHT_STATUS_BAR to flag
            activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(Color.WHITE); // optional
        }
    }
}