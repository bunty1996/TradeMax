package com.trademax.Fragments.DeliveredOrders.view;

import android.app.Activity;

public interface DeliveredOrderview {

    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity, String message);
}
