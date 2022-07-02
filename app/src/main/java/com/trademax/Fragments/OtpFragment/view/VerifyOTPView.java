package com.trademax.Fragments.OtpFragment.view;

import android.app.Activity;

public interface VerifyOTPView {

    public void showDialog(Activity activity);

    public void hideDialog();

    public void showMessage(Activity activity, String message);

    public void changeFragment();

}
