package com.trademax.Activities.Splash.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

import com.trademax.Activities.HomeBottomNavigationActivity;
import com.trademax.Fragments.Login_Register.LoginSignUpFragment;

public class SplashPresenter implements SplashInterface {

    private final String loginValue;
    private Activity activity;

    public SplashPresenter(Activity activity, String loginValue) {
        this.activity = activity;
        this.loginValue = loginValue;
    }

    @Override
    public void nextscreen() {
        handler();
    }

    /*
   This is handler for check ststus and move to next screen
    */
    private void handler() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (loginValue.equalsIgnoreCase("true")) {
                    Intent intent2 = new Intent(activity, HomeBottomNavigationActivity.class);
                    activity.startActivity(intent2);
                    activity.finishAffinity();

                } else {
                    Intent intent = new Intent(activity, LoginSignUpFragment.class);
                    activity.startActivity(intent);
                    activity.finishAffinity();

                }
            }
        }, 1500);
    }


}
