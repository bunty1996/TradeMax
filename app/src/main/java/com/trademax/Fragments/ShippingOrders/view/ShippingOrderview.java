package com.trademax.Fragments.ShippingOrders.view;

import android.app.Activity;

public interface ShippingOrderview {

    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity, String message);
}
