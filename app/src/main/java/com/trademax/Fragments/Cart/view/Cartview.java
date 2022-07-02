package com.trademax.Fragments.Cart.view;

import android.app.Activity;

import com.trademax.Models.CartListModel.CartlistData;

import java.util.ArrayList;

public interface Cartview {
    public void showDialog(Activity activity);

    public void hideDialog();

    public void showMessage(Activity activity, String message);

    public void setData(ArrayList<CartlistData> data);
    public void settotal(ArrayList<CartlistData> data);
}
