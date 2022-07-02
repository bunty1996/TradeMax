package com.trademax.Activities.HomeItemsActivity.view;

import android.app.Activity;

public interface Homeitemview {
    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity, String message);
    public void changeFavouriteImage(String message);
}
