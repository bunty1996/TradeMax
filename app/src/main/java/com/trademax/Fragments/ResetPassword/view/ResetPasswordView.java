package com.trademax.Fragments.ResetPassword.view;

import android.app.Activity;

public interface ResetPasswordView {

    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity, String message);

    public void changeFragment();
}
