package com.trademax.Activities.Changepassword.presenter;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

import com.trademax.Activities.Changepassword.view.ChangePasswordView;
import com.trademax.Handler.ChangePassHandler;
import com.trademax.Models.ChangePassword.ChangePassExample;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;

public class ChangePasswordPresenter {

    private Activity activity;
    private ChangePasswordView changePasswordView;
    private EditText et_oldPassword;
    private EditText et_newPassword;
    private EditText et_confirmPassword;

    public ChangePasswordPresenter(Activity activity, ChangePasswordView changePasswordView) {
        this.activity = activity;
        this.changePasswordView = changePasswordView;

    }

    public void changePasswordMethod(EditText et_oldPassword, EditText et_newPassword, EditText et_confirmPassword) {

        this.et_oldPassword = et_oldPassword;
        this.et_newPassword = et_newPassword;
        this.et_confirmPassword = et_confirmPassword;

        String token = CSPreferences.readString(activity, Utils.TOKEN);
        String userId = CSPreferences.readString(activity, Utils.USERID);

        if (checkValidation()) {
            changePasswordView.showDialog(activity);
            WebServices.getmInstance().changePasswordMethod(token, "application/json", userId, et_oldPassword.getText().toString().trim(), et_newPassword.getText().toString().trim(), new ChangePassHandler() {
                @Override
                public void onSuccess(ChangePassExample changePassExample) {
                    changePasswordView.hideDialog();
                    if (changePassExample != null) {
                        if (changePassExample.getIsSuccess() == true) {
                            changePasswordView.showMessage(activity, changePassExample.getMessage());

                            activity.finish();

                        } else {
                            changePasswordView.showMessage(activity, changePassExample.getMessage());
                        }
                    }
                }

                @Override
                public void onError(String message) {
                    changePasswordView.hideDialog();
                    changePasswordView.showMessage(activity, message);
//                    Toast.makeText(activity, "Please try again", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private boolean checkValidation() {
        boolean temp = true;
        String oldPass = et_oldPassword.getText().toString();
        String new_Pass = et_newPassword.getText().toString();
        String confrmPass = et_confirmPassword.getText().toString();
        if (oldPass.toString().trim().length() == 0) {
            Toast.makeText(activity, "Please enter old password", Toast.LENGTH_SHORT).show();
            temp = false;
        } else if (new_Pass.toString().trim().length() == 0) {
            Toast.makeText(activity, "Please enter new password", Toast.LENGTH_SHORT).show();
            temp = false;
        } else if (confrmPass.toString().trim().length() == 0) {
            Toast.makeText(activity, "Please enter confirm password", Toast.LENGTH_SHORT).show();
            temp = false;
        } else if (!new_Pass.equals(confrmPass)) {
            Toast.makeText(activity, "Password must be same", Toast.LENGTH_SHORT).show();
            temp = false;
        }
        return temp;
    }
}
