package com.trademax.Fragments.search.view;

import android.app.Activity;

public interface SearchCategoriesDetails_View {
    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity, String message);
    public void changeFragment();
}
