package com.trademax.Activities.Builder.view;

import android.app.Activity;

public interface BuilderView {
    public void showDialog(Activity activity);

    public void hideDialog();

    public void showMessage(Activity activity, String message);
}
