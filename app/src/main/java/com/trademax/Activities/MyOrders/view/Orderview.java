package com.trademax.Activities.MyOrders.view;

import android.app.Activity;

public interface Orderview {

    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity, String message);
    public void setAdapter();
}
