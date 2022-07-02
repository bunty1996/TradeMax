package com.trademax.Fragments.ForgotPass.presenter;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.trademax.Fragments.ForgotPass.view.ForgotPasswordView;
import com.trademax.Handler.ForgotPassHandler;
import com.trademax.Models.ForgotPass.ForgotPassExample;
import com.trademax.R;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;

public class ForgotpasswordPresenter {

    private final Activity activity;
    private final ForgotPasswordView forgotPasswordView;
    private EditText et_email;


    public ForgotpasswordPresenter(Activity activity, ForgotPasswordView forgotPasswordView) {
        this.activity = activity;
        this.forgotPasswordView = forgotPasswordView;

    }

    public void hitForgotPass(EditText et_email) {
        this.et_email = et_email;
        if (Utils.isNetworkConnected(activity)) {
            if (checkValidation()) {
                forgotPasswordView.showDialog(activity);
                WebServices.getmInstance().forgotPassMethod(et_email.getText().toString().trim(), new ForgotPassHandler() {
                    @Override
                    public void onSuccess(ForgotPassExample forgotPassExample) {
                        forgotPasswordView.hideDialog();
                        if (forgotPassExample != null) {
                            if (forgotPassExample.getIsSuccess() == true) {
                                forgotPasswordView.showMessage(activity, forgotPassExample.getMessage());
//                            Toast.makeText(activity, forgotPassExample.getMessage(), Toast.LENGTH_SHORT).show();
                                CSPreferences.putString(activity, Utils.TOKEN, forgotPassExample.getData().getToken());

                                forgotPasswordView.changeFragment();

                            } else {
                                forgotPasswordView.showMessage(activity, forgotPassExample.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onError(String message) {
                        forgotPasswordView.hideDialog();
//                    login_registerView.showMessage(activity, "Please try again");
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } else {
            Toast.makeText(activity, R.string.internet_connection, Toast.LENGTH_SHORT).show();
        }

    }

    public boolean checkValidation() {
        if (et_email.getText().toString().trim().length() == 0) {
//            login_registerView.showMessage(activity, "Please enter email");
            Toast.makeText(activity, "Please enter email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!(isValidEmail(et_email.getText().toString().trim()))) {
//            login_registerView.showMessage(activity, "Please enter valid email");
            Toast.makeText(activity, "Please enter valid email", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
