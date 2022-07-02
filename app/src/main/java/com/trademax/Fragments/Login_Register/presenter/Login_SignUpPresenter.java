package com.trademax.Fragments.Login_Register.presenter;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.trademax.Activities.HomeBottomNavigationActivity;
import com.trademax.Activities.MainActivity;
import com.trademax.Fragments.ForgotPass.ForgotPasswordFragment;
import com.trademax.Fragments.Login_Register.LoginSignUpFragment;
import com.trademax.Fragments.Login_Register.view.Login_RegisterView;
import com.trademax.Handler.LoginHandler;
import com.trademax.Handler.RegisterHandler;
import com.trademax.Handler.SocialLoginHandler;
import com.trademax.Models.Login.LoginExample;
import com.trademax.Models.Register.SignUpExample;
import com.trademax.Models.SocialLogin.SocialLoginExample;
import com.trademax.R;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;

public class Login_SignUpPresenter {

    private final Activity activity;
    private final Login_RegisterView login_registerView;
    private EditText et_loginEmail;
    private EditText et_loginPassword;

    private EditText et_signUpName;
    private EditText et_signUpEmail;
    private EditText et_signUpPassword;

    public Login_SignUpPresenter(Activity activity, Login_RegisterView login_registerView) {
        this.activity = activity;
        this.login_registerView = login_registerView;
    }

    public void hitLogin(EditText et_loginEmail, EditText et_loginPassword) {

        this.et_loginEmail = et_loginEmail;
        this.et_loginPassword = et_loginPassword;

        if (Utils.isNetworkConnected(activity)) {
            if (checkValidation()) {
                login_registerView.showDialog(activity);

                WebServices.getmInstance().loginMethod(et_loginEmail.getText().toString().trim(), et_loginPassword.getText().toString().trim(), new LoginHandler() {
                    @Override
                    public void onSuccess(LoginExample loginExample) {
                        login_registerView.hideDialog();
                        if (loginExample != null) {
                            if (loginExample.getIsSuccess() == true) {
                                login_registerView.showMessage(activity, "Login Successfully");
//                            Toast.makeText(activity, "Login Successfully", Toast.LENGTH_SHORT).show();

                                CSPreferences.putString(activity, Utils.USERLOGIN, "true");
                                CSPreferences.putString(activity, Utils.USERNAME, loginExample.getData().getFullname());
                                CSPreferences.putString(activity, Utils.USEREMAIL, loginExample.getData().getEmail());
                                CSPreferences.putString(activity, Utils.USERPHOTO, loginExample.getData().getImage());
                                CSPreferences.putString(activity, Utils.USERID, loginExample.getData().getId());
                                CSPreferences.putString(activity, Utils.TOKEN, loginExample.getData().getToken());
//                            CSPreferences.putString(activity, Utils.LOGINTYPE, "normal");

                                Intent homeIntent = new Intent(activity, HomeBottomNavigationActivity.class);
                                activity.startActivity(homeIntent);
                                activity.finishAffinity();

                            } else {
                                login_registerView.showMessage(activity, loginExample.getMessage());
                            }
                        } else {
                            login_registerView.showMessage(activity, loginExample.getMessage());
                        }

                    }

                    @Override
                    public void onError(String message) {
                        login_registerView.hideDialog();
                        login_registerView.showMessage(activity, message);
//                    Toast.makeText(activity, "Please try again", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } else {
            Toast.makeText(activity, R.string.internet_connection, Toast.LENGTH_SHORT).show();
        }

    }

    public boolean checkValidation() {
        if (et_loginEmail.getText().toString().trim().length() == 0) {
            login_registerView.showMessage(activity, "Please enter email");
//            Toast.makeText(activity, "Please enter email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!(isValidEmail(et_loginEmail.getText().toString().trim()))) {
            login_registerView.showMessage(activity, "Please enter valid email");
//            Toast.makeText(activity, "Please enter valid email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (et_loginPassword.getText().toString().trim().length() == 0) {
            login_registerView.showMessage(activity, "Please enter password");
//            Toast.makeText(activity, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void hitRegister(EditText et_signUpName, EditText et_signUpEmail, EditText et_signUpPassword) {

        this.et_signUpName = et_signUpName;
        this.et_signUpEmail = et_signUpEmail;
        this.et_signUpPassword = et_signUpPassword;

        if (Utils.isNetworkConnected(activity)) {
            if (checkValidationSignUp()) {
                login_registerView.showDialog(activity);
                WebServices.getmInstance().registerMethod(et_signUpName.getText().toString().trim(), et_signUpEmail.getText().toString().trim(),
                        et_signUpPassword.getText().toString().trim(), new RegisterHandler() {
                            @Override
                            public void onSuccess(SignUpExample signUpExample) {
                                login_registerView.hideDialog();
                                if (signUpExample != null) {
                                    if (signUpExample.getIsSuccess() == true) {
                                        login_registerView.showMessage(activity, signUpExample.getMessage());
//                                    Toast.makeText(activity, "Registered Successfully", Toast.LENGTH_SHORT).show();

                                        CSPreferences.putString(activity, Utils.USERLOGIN, "true");
                                        CSPreferences.putString(activity, Utils.USERNAME, signUpExample.getData().getFullname());
                                        CSPreferences.putString(activity, Utils.USEREMAIL, signUpExample.getData().getEmail());
                                        CSPreferences.putString(activity, Utils.USERPHOTO, signUpExample.getData().getImage());
                                        CSPreferences.putString(activity, Utils.USERID, signUpExample.getData().getId());
                                        CSPreferences.putString(activity, Utils.TOKEN, signUpExample.getData().getToken());


//                                    CSPreferences.putString(activity, Utils.USERPHOTO, "");

//                                        Utils.changeFragment(activity, new LoginSignUpFragment());

                                        Intent homeIntent = new Intent(activity, MainActivity.class);
                                        activity.startActivity(homeIntent);
                                        activity.finishAffinity();

                                    } else {
                                        login_registerView.showMessage(activity, signUpExample.getMessage());
//                                    Toast.makeText(activity, signUpExample.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
//                            else {
//                                login_registerView.showMessage(activity, signUpExample.getMessage());
//                            }
                            }

                            @Override
                            public void onError(String message) {
                                login_registerView.hideDialog();
                                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        } else {
            Toast.makeText(activity, R.string.internet_connection, Toast.LENGTH_SHORT).show();
        }

    }

    public boolean checkValidationSignUp() {
        if (et_signUpName.getText().toString().trim().length() == 0) {
            login_registerView.showMessage(activity, "Please enter name");
//            Toast.makeText(activity, "Please enter name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (et_signUpEmail.getText().toString().trim().length() == 0) {
            login_registerView.showMessage(activity, "Please enter email");
//            Toast.makeText(activity, "Please enter email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!(isValidEmail(et_signUpEmail.getText().toString().trim()))) {
            login_registerView.showMessage(activity, "Please enter valid email");
//            Toast.makeText(activity, "Please enter valid email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (et_signUpPassword.getText().toString().trim().length() == 0) {
            login_registerView.showMessage(activity, "Please enter password");
//            Toast.makeText(activity, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

//    public void socialLogin(String name, String email, String id, String token, String loginType, String photourl) {
//        login_registerView.showDialog(activity);
//        WebServices.getmInstance().getsociallogin(id, loginType, email, name, photourl, "", "", new SocialLoginHandler() {
//            @Override
//            public void onSuccess(SocialLoginExample socialLoginExample) {
//                login_registerView.hideDialog();
//                if (socialLoginExample != null) {
//                    if (socialLoginExample.getIsSuccess() == true) {
//
//                        //  CSPreferences.putString(activity,Util.USERID, loginExample.getResponse().getData().getId() + "");
//                        CSPreferences.putString(activity, Utils.USERNAME, socialLoginExample.getData().getFullname());
//                        CSPreferences.putString(activity, Utils.USERPHOTO, socialLoginExample.getData().getImage());
//                        CSPreferences.putString(activity, Utils.USEREMAIL, socialLoginExample.getData().getEmail());
//                        CSPreferences.putString(activity, Utils.USERID, socialLoginExample.getData().getId());
//                        CSPreferences.putString(activity, Utils.USERLOGIN, "true");
//                        CSPreferences.putString(activity, Utils.LOGINTYPE, socialLoginExample.getData().getSocialType());
//
//                        Intent intent = new Intent(activity, HomeBottomNavigationActivity.class);
//                        activity.startActivity(intent);
//                        activity.finishAffinity();
//
//                    } else {
//                        login_registerView.showMessage(activity, socialLoginExample.getMessage());
//                        Log.e("cdbkfdvb", socialLoginExample.getMessage());
//                    }
//                }
//            }
//
//            @Override
//            public void onError(String message) {
//                login_registerView.hideDialog();
//                login_registerView.showMessage(activity, message);
//            }
//        });
//    }

    public void fbsocialLogin(String name, String email, String id, String token, String
            loginType, String photourl) {
        login_registerView.showDialog(activity);
        WebServices.getmInstance().getfbsociallink(id, loginType, email, name, photourl, "", "", new SocialLoginHandler() {
            @Override
            public void onSuccess(SocialLoginExample socialLoginExample) {
                login_registerView.hideDialog();

                if (socialLoginExample != null) {
                    if (socialLoginExample.getIsSuccess() == true) {

                        //  CSPreferences.putString(activity,Util.USERID, loginExample.getResponse().getData().getId() + "");
                        CSPreferences.putString(activity, Utils.USERNAME, socialLoginExample.getData().getFullname());
                        CSPreferences.putString(activity, Utils.USERPHOTO, socialLoginExample.getData().getImage());
                        CSPreferences.putString(activity, Utils.USEREMAIL, socialLoginExample.getData().getEmail());
                        CSPreferences.putString(activity, Utils.USERID, socialLoginExample.getData().getId());
                        CSPreferences.putString(activity, Utils.USERLOGIN, "true");
                        CSPreferences.putString(activity, Utils.LOGINTYPE, socialLoginExample.getData().getSocialType());

                        Intent intent = new Intent(activity, HomeBottomNavigationActivity.class);
                        activity.startActivity(intent);
                        activity.finishAffinity();

                    } else {
                        login_registerView.showMessage(activity, socialLoginExample.getMessage());
                        Log.e("cdbkfdvb", socialLoginExample.getMessage());
                    }
                }
            }

            @Override
            public void onError(String message) {
                login_registerView.hideDialog();
                login_registerView.showMessage(activity, message);
            }
        });
    }
}
