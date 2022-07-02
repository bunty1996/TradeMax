package com.trademax.Activities.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.trademax.Activities.HomeBottomNavigationActivity;
import com.trademax.Activities.MainActivity;
import com.trademax.R;
import com.trademax.Utils.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SplashActivity extends AppCompatActivity {

    public static final int MY_PERMISSIONS_REQUEST_WRITE_FIELS = 102;
    private static final int REQUEST = 112;
    Activity activity;
    private Dialog dialog;

    private SharedPreferences pref;
    private String loginValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLightStatusBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        activity = this;
        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
//        loginValue = pref.getString(Utils.USERLOGIN, "");
        init();

        try {
            PackageInfo info = activity.getPackageManager().getPackageInfo(
                    "com.trademax",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", "KeyHash:" + Base64.encodeToString(md.digest(),
                        Base64.DEFAULT));

                Log.e("SHA1", String.valueOf(md));

            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    private void init() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                if (loginValue.equalsIgnoreCase("true")) {
                Intent intent2 = new Intent(activity, HomeBottomNavigationActivity.class);
                startActivity(intent2);
                finishAffinity();

//                } else {
//
////                    LoginSignUpFragment fragment = new LoginSignUpFragment();
////                    FragmentManager fm = getSupportFragmentManager();
////                    FragmentTransaction transaction = fm.beginTransaction();
////                    transaction.add(R.id.mainContainer, fragment);
////                    transaction.commit();
////                    finishAffinity();
//
////                    Intent intent = new Intent(activity, MainActivity.class);
//                    Intent intent = new Intent(activity, HomeBottomNavigationActivity.class);
//                    startActivity(intent);
//                    finishAffinity();

//                }
            }
        }, 1500);
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