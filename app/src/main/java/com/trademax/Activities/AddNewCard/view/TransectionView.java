package com.trademax.Activities.AddNewCard.view;

import android.app.Activity;

public interface TransectionView {
    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity, String message);

}
