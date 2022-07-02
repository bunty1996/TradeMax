package com.trademax.Activities.ShippingAddress.view;

import android.app.Activity;

public interface ShippingAddressView {
    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity, String message);
}
