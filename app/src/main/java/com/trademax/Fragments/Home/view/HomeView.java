package com.trademax.Fragments.Home.view;

import android.app.Activity;

import com.trademax.Models.GetBanner.BannerDatum;

import java.util.List;

public interface HomeView {
    public void showDialog(Activity activity);

    public void hideDialog();

    public void showMessage(Activity activity, String message);

    public void setBannerImages(List<BannerDatum> data);
}
