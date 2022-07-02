package com.trademax.Fragments.Login_Register.view;

import android.app.Activity;

public interface Login_RegisterView {
    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity, String message);
}
