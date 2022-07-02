package com.trademax.Fragments.Favorites.presenter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.trademax.Activities.FavouriteItem.FavouriteitemActivity;
import com.trademax.Adapters.FavouriteGridAdapter;
import com.trademax.Fragments.Favorites.FavoritesFragment;
import com.trademax.Fragments.Favorites.view.FavouriteView;
import com.trademax.Handler.FavouriteHandler;
import com.trademax.Handler.PainterPlasterLikeProductHandler;
import com.trademax.Models.Favourite.FavouriteDatum;
import com.trademax.Models.Favourite.FavouriteExample;
import com.trademax.Models.PainterPlasterLikeProduct.PainterPlasterLikeProductExample;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;
import com.google.gson.Gson;

import java.util.ArrayList;

public class FavouritePresenter {
    private static final String TAG = "FavouritePresenter";

    private Activity activity;
    private FavouriteView homeView;
    private GridView gridView;
    private LinearLayout linear_emptyWishlist;


    public FavouritePresenter(Activity activity, FavoritesFragment homeView, GridView gridView, LinearLayout linear_emptyWishlist) {
        this.activity = activity;
        this.homeView = homeView;
        this.gridView = gridView;
        this.linear_emptyWishlist = linear_emptyWishlist;
    }

    public void getFavoriteDataMethod() {
        homeView.showDialog(activity);
        String userId = CSPreferences.readString(activity, Utils.USERID);
        if (userId == ""){
            userId = null;
        }
        WebServices.getmInstance().getFavouriteDataMethod(userId, new FavouriteHandler() {
            @Override
            public void onSuccess(FavouriteExample favouriteExample) {
                homeView.hideDialog();
                if (favouriteExample != null) {
                    if (favouriteExample.getIsSuccess() == true) {
//                        homeView.showMessage(activity, favouriteExample.getMessage());
                        if (favouriteExample.getData().isEmpty()) {
                            gridView.setVisibility(View.GONE);
                            linear_emptyWishlist.setVisibility(View.VISIBLE);
                        } else {
                            gridView.setVisibility(View.VISIBLE);
                            linear_emptyWishlist.setVisibility(View.GONE);
                            addlist(favouriteExample.getData());

                        }

                    } else {
                        homeView.showMessage(activity, favouriteExample.getMessage());
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

    }

    public void HomeLikeMeathod(String productId) {
        String userId = CSPreferences.readString(activity, Utils.USERID);
        homeView.showDialog(activity);
        WebServices.getmInstance().getlikeProductMethod(null, productId, new PainterPlasterLikeProductHandler() {
            @Override
            public void onSuccess(PainterPlasterLikeProductExample painterPlasterLikeProductExample) {
                homeView.hideDialog();
                if (painterPlasterLikeProductExample != null) {
                    if (painterPlasterLikeProductExample.getIsSuccess() == true) {
                        homeView.showMessage(activity, painterPlasterLikeProductExample.getMessage());
                    } else {
                        homeView.showMessage(activity, painterPlasterLikeProductExample.getMessage());
                    }
                }
            }

            @Override
            public void onError(String message) {
                homeView.hideDialog();
                homeView.showMessage(activity, message);
            }
        });
    }

    private void addlist(ArrayList<FavouriteDatum> categoriesData) {
        FavouriteGridAdapter adapter = new FavouriteGridAdapter(activity, categoriesData);
        gridView.setAdapter(adapter);
        adapter.ViewClickListner(new FavouriteGridAdapter.OnViewClick() {
            @Override
            public void onitemclick(int position) {
                String data = new Gson().toJson(categoriesData.get(position).getProduct());
                Intent intent = new Intent(activity, FavouriteitemActivity.class);
                intent.putExtra("arraylistData", data);
                activity.startActivity(intent);
            }

            @Override
            public void onitemremove(int position) {
                String productid = categoriesData.get(position).getProduct().getId();
                Log.d(TAG, "onitemremove" + productid);
                HomeLikeMeathod(productid);
                categoriesData.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

    }
}
