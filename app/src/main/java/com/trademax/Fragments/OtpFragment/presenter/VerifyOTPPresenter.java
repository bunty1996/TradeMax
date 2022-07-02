package com.trademax.Fragments.OtpFragment.presenter;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.trademax.Fragments.OtpFragment.view.VerifyOTPView;
import com.trademax.Handler.ForgotPassHandler;
import com.trademax.Handler.VerifyOTPHandler;
import com.trademax.Models.ForgotPass.ForgotPassExample;
import com.trademax.Models.VerifyOTP.VerifyOTPExample;
import com.trademax.R;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;

public class VerifyOTPPresenter {

    private final Activity activity;
    private final VerifyOTPView verifyOTPView;

    private String code;

    public VerifyOTPPresenter(Activity activity, VerifyOTPView verifyOTPView) {
        this.activity = activity;
        this.verifyOTPView = verifyOTPView;
    }

    public void hitVerifyOtp(String code) {
        this.code = code;
        String token = CSPreferences.readString(activity, Utils.TOKEN);
        verifyOTPView.showDialog(activity);

        if (Utils.isNetworkConnected(activity)) {
            WebServices.getmInstance().verifyOTPMethod(token, code.trim(), new VerifyOTPHandler() {
                @Override
                public void onSuccess(VerifyOTPExample verifyOTPExample) {
                    verifyOTPView.hideDialog();
                    if (verifyOTPExample != null) {
                        if (verifyOTPExample.getIsSuccess() == true) {
                            verifyOTPView.showMessage(activity, verifyOTPExample.getMessage());

//                                    CSPreferences.putString(activity, Utils.USERLOGIN, "true");
//                                    CSPreferences.putString(activity, Utils.otpToken, verifyOTPExample.get);
//                                    CSPreferences.putString(activity, Utils.USEREMAIL, signUpExample.getData().getEmail());
//                                    CSPreferences.putString(activity, Utils.USERPHOTO, signUpExample.getData().getImage());
//                                    CSPreferences.putString(activity, Utils.USERPHOTO, "");

                            verifyOTPView.changeFragment();

                        } else {
                            verifyOTPView.showMessage(activity, verifyOTPExample.getMessage());
//                                    Toast.makeText(activity, signUpExample.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        verifyOTPView.showMessage(activity, verifyOTPExample.getMessage());
                    }
                }

                @Override
                public void onError(String message) {
                    verifyOTPView.hideDialog();
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, R.string.internet_connection, Toast.LENGTH_SHORT).show();
        }
    }

    /// Resend OTP

    public void hitForgotPass() {

        String emaiId = CSPreferences.readString(activity, Utils.USEREMAIL);
        if (checkValidation(emaiId)) {
            verifyOTPView.showDialog(activity);
            WebServices.getmInstance().forgotPassMethod(emaiId.trim(), new ForgotPassHandler() {
                @Override
                public void onSuccess(ForgotPassExample forgotPassExample) {
                    verifyOTPView.hideDialog();
                    if (forgotPassExample != null) {
                        if (forgotPassExample.getIsSuccess() == true) {
                            verifyOTPView.showMessage(activity, forgotPassExample.getMessage());
//                            Toast.makeText(activity, forgotPassExample.getMessage(), Toast.LENGTH_SHORT).show();
                            CSPreferences.putString(activity, Utils.TOKEN, forgotPassExample.getData().getToken());

                        } else {
                            verifyOTPView.showMessage(activity, forgotPassExample.getMessage());
                        }
                    }
                }

                @Override
                public void onError(String message) {
                    verifyOTPView.hideDialog();
//                    login_registerView.showMessage(activity, "Please try again");
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public boolean checkValidation(String emaiId) {
        if (emaiId.toString().trim().length() == 0) {
//            login_registerView.showMessage(activity, "Please enter email");
            Toast.makeText(activity, "Please enter email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!(isValidEmail(emaiId.trim()))) {
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
