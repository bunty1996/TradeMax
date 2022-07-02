package com.trademax.Fragments.ForgotPass.view;

import android.app.Activity;

public interface ForgotPasswordView {

    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity, String message);
    public void changeFragment();
}
