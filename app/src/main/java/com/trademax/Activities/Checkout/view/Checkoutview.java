package com.trademax.Activities.Checkout.view;

import android.app.Activity;

public interface Checkoutview {
    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity, String message);
}
