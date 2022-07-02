package com.trademax.Fragments.Home.presenter;

import android.app.Activity;
import android.content.Intent;
import android.widget.GridView;
import android.widget.Toast;

import com.trademax.Activities.HomeItemsActivity.HomeItemsActivity;
import com.trademax.Adapters.HomeGridAdapter;
import com.trademax.Fragments.Home.view.HomeView;
import com.trademax.Handler.BannerHandler;
import com.trademax.Handler.HomeGetAllHandler;
import com.trademax.Models.GetBanner.BannerExample;
import com.trademax.Models.HomeGetAll.GetAllProductsExample;
import com.trademax.Models.HomeGetAll.GetAllProductsItem;
import com.trademax.R;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;
import com.google.gson.Gson;

import java.util.ArrayList;

public class HomePresenter {

    private Activity activity;
    private HomeView homeView;

    public HomePresenter(Activity activity, HomeView homeView) {
        this.activity = activity;
        this.homeView = homeView;
    }

    public void getHomeDataMethod(GridView homedetails) {
        String userId = CSPreferences.readString(activity, Utils.USERID);
        if (userId == ""){
            userId = null;
        }
        homeView.showDialog(activity);
        if (Utils.isNetworkConnected(activity)) {
            WebServices.getmInstance().getHomeDataMethod(userId, 1, 50, new HomeGetAllHandler() {
                @Override
                public void onSuccess(GetAllProductsExample getAllProductsExample) {
                    homeView.hideDialog();
                    if (getAllProductsExample != null) {
                        if (getAllProductsExample.getIsSuccess() == true) {
//                        homeView.showMessage(activity, getAllProductsExample.getMessage());
//                            Toast.makeText(activity, forgotPassExample.getMessage(), Toast.LENGTH_SHORT).show();
//                        CSPreferences.putString(activity, Utils.OTPTOKEN,getAllProductsExample.getData().getToken());
                            HomeGridAdapter adapter = new HomeGridAdapter(activity, (ArrayList<GetAllProductsItem>) getAllProductsExample.getItems());
                            homedetails.setAdapter(adapter);
                            adapter.ViewClickListner(new HomeGridAdapter.OnViewClick() {
                                @Override
                                public void onViewClick(int position) {

                                    String data = new Gson().toJson(getAllProductsExample.getItems().get(position));
                                    Intent intent = new Intent(activity, HomeItemsActivity.class);
                                    intent.putExtra("arraylistData", data);
                                    activity.startActivity(intent);
                                }
                            });
                        } else {
                            homeView.showMessage(activity, getAllProductsExample.getMessage());
                        }
                    }
                }

                @Override
                public void onError(String message) {
                    homeView.hideDialog();
//                    login_registerView.showMessage(activity, "Please try again");
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            homeView.hideDialog();
            Toast.makeText(activity, R.string.internet_connection, Toast.LENGTH_SHORT).show();
        }

    }

    public void getBannersMethod() {
        String token = CSPreferences.readString(activity, Utils.TOKEN);
        if (Utils.isNetworkConnected(activity)) {
//        homeView.showDialog(activity);
            WebServices.getmInstance().getBannermethod("", new BannerHandler() {
                @Override
                public void onSuccess(BannerExample bannerExample) {
//                homeView.hideDialog();
                    if (bannerExample != null) {
                        if (bannerExample.getIsSuccess() == true) {
                            homeView.setBannerImages(bannerExample.getData());
                        } else {
                            homeView.showMessage(activity, bannerExample.getMessage());
                        }
                    }
                }

                @Override
                public void onError(String message) {
//                homeView.hideDialog();
                    homeView.showMessage(activity, message);
                }
            });
        } else {
            Toast.makeText(activity, R.string.internet_connection, Toast.LENGTH_SHORT).show();
        }
    }

}
