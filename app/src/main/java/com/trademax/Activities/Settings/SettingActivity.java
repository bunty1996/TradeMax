package com.trademax.Activities.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.trademax.Activities.Changepassword.ChangePasswordActivity;
import com.trademax.Activities.EditProfile.EditProfileActivity;
import com.trademax.Activities.Settings.view.SettingView;
import com.trademax.R;
import com.trademax.Utils.Utils;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener, SettingView {

    private Activity activity;
    private ImageView img_back;

    private LinearLayout li_edit_profile;
    private LinearLayout li_changePass;
    private LinearLayout li_notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLightStatusBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        activity = this;
        init();
        listiners();
    }

    private void init() {

        li_edit_profile = findViewById(R.id.li_edit_profile);
        li_changePass = findViewById(R.id.li_changePass);
        li_notification = findViewById(R.id.li_notification);
        img_back = findViewById(R.id.img_back);
    }

    private void listiners() {

        li_edit_profile.setOnClickListener(this);
        li_changePass.setOnClickListener(this);
        li_notification.setOnClickListener(this);
        img_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == li_edit_profile) {
            Intent intent = new Intent(activity, EditProfileActivity.class);
            startActivity(intent);

        } else if (v == li_changePass) {

            Intent intent = new Intent(activity, ChangePasswordActivity.class);
            startActivity(intent);

        } else if (v == li_notification) {

//            Intent intent = new Intent(activity, EditProfileActivity.class);
//            startActivity(intent);
        } else if (v == img_back) {
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

    @Override
    public void showDialog(Activity activity) {
        Utils.showDialogMethod(activity, activity.getFragmentManager());
    }

    @Override
    public void hideDialog() {
        Utils.hideDialog();
    }

    @Override
    public void showMessage(Activity activity, String message) {
        Utils.showMessage(activity, message);
    }
}