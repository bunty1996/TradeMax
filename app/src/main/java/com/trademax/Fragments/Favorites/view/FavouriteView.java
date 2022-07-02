package com.trademax.Fragments.Favorites.view;

import android.app.Activity;

public interface FavouriteView {
    public void showDialog(Activity activity);

    public void hideDialog();

    public void showMessage(Activity activity, String message);
}
