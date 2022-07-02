package com.trademax.Activities.SuccessScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.trademax.Activities.HomeBottomNavigationActivity;
import com.trademax.R;

public class SuccessActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_continueShopping;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        activity = this;
        init();
        listiners();
    }

    private void listiners() {
        btn_continueShopping.setOnClickListener(this);
    }

    private void init() {
        btn_continueShopping = findViewById(R.id.btn_continueShopping);
    }

    @Override
    public void onClick(View v) {

        if (v == btn_continueShopping) {
            Intent intent = new Intent(activity, HomeBottomNavigationActivity.class);
            startActivity(intent);
            finishAffinity();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();

    }
}