package com.trademax.Activities.Changepassword;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.trademax.Activities.Changepassword.presenter.ChangePasswordPresenter;
import com.trademax.Activities.Changepassword.view.ChangePasswordView;
import com.trademax.R;
import com.trademax.Utils.Utils;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener, ChangePasswordView {

    private ImageView img_back;
    private Activity activity;
    private EditText et_oldPassword;
    private EditText et_newPassword;
    private EditText et_confirmPassword;
    private Button btn_login;
    private ChangePasswordPresenter changePasswordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLightStatusBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        activity = this;
        init();
        listiners();

        changePasswordPresenter = new ChangePasswordPresenter(activity,this);
    }

    private void init() {
        img_back = findViewById(R.id.img_back);
        et_oldPassword = findViewById(R.id.et_oldPassword);
        et_newPassword = findViewById(R.id.et_newPassword);
        et_confirmPassword = findViewById(R.id.et_confirmPassword);
        btn_login = findViewById(R.id.btn_login);
    }

    private void listiners() {
        img_back.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == img_back) {
            finish();
        }else if (v == btn_login){
            changePasswordPresenter.changePasswordMethod(et_oldPassword,et_newPassword,et_confirmPassword);
        }
    }

    private void setLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = activity.getWindow().getDecorView().getSystemUiVisibility(); // get current flag
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;   // add LIGHT_STATUS_BAR to flag
            activity.getWindow().getDecorView().setSystemUiVisibility(flags);
//            activity.getWindow().setStatusBarColor(getResources().getColor(R.color.appcolor));
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT); // optional
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