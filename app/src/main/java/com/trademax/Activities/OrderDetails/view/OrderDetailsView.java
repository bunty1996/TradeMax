package com.trademax.Activities.OrderDetails.view;

import android.app.Activity;

public interface OrderDetailsView {

    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity, String message);
}
