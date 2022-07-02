package com.trademax.Activities.EditProfile.view;

import android.app.Activity;

public interface EditProfileView {

    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity, String message);
}
