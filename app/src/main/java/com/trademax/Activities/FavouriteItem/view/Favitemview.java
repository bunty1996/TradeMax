package com.trademax.Activities.FavouriteItem.view;

import android.app.Activity;

public interface Favitemview {
    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity, String message);
    public void changeFavouriteImage(String message);

}
