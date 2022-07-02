package com.trademax.Activities.Settings.view;

import android.app.Activity;

public interface SettingView {
    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity, String message);
}
