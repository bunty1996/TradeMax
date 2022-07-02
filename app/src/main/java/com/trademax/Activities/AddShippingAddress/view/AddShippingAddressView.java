package com.trademax.Activities.AddShippingAddress.view;

import android.app.Activity;

public interface AddShippingAddressView {

    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity, String message);
}
