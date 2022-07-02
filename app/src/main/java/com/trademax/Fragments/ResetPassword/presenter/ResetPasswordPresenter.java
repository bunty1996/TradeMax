package com.trademax.Fragments.ResetPassword.presenter;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

import com.trademax.Fragments.ResetPassword.view.ResetPasswordView;
import com.trademax.Handler.ResetPassHandler;
import com.trademax.Models.ResetPass.ResetPaswordExample;
import com.trademax.R;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;

public class ResetPasswordPresenter {

    private final Activity activity;
    private final ResetPasswordView resetPasswordView;
    private EditText et_newPassword;
    private EditText et_confirmPassword;


    public ResetPasswordPresenter(Activity activity, ResetPasswordView resetPasswordView) {
        this.activity = activity;
        this.resetPasswordView = resetPasswordView;

    }

    public void hitConfirmPass(EditText et_newPassword, EditText et_confirmPassword) {
        this.et_newPassword = et_newPassword;
        this.et_confirmPassword = et_confirmPassword;

//        String userId = CSPreferences.readString(activity, Utils.USERID);
        String token = CSPreferences.readString(activity, Utils.TOKEN);
        if (Utils.isNetworkConnected(activity)) {
            if (checkValidation()) {
                resetPasswordView.showDialog(activity);
                WebServices.getmInstance().resetPasswordMethod(token, et_confirmPassword.getText().toString().trim(), new ResetPassHandler() {
                    @Override
                    public void onSuccess(ResetPaswordExample resetPaswordExample) {
                        resetPasswordView.hideDialog();
                        if (resetPaswordExample != null) {
                            if (resetPaswordExample.getIsSuccess() == true) {
                                resetPasswordView.showMessage(activity, resetPaswordExample.getMessage());

                                resetPasswordView.changeFragment();

                            } else {
                                resetPasswordView.showMessage(activity, resetPaswordExample.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onError(String message) {
                        resetPasswordView.hideDialog();
                        resetPasswordView.showMessage(activity, message);
//                    Toast.makeText(activity, "Please try again", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }else {
            Toast.makeText(activity, R.string.internet_connection, Toast.LENGTH_SHORT).show();
        }

    }

    private boolean checkValidation() {
        boolean temp = true;
        String pass = et_newPassword.getText().toString();
        String cpass = et_confirmPassword.getText().toString();
        if (!pass.equals(cpass)) {
            Toast.makeText(activity, "Password must be same", Toast.LENGTH_SHORT).show();
            temp = false;
        }
        return temp;
    }
}
