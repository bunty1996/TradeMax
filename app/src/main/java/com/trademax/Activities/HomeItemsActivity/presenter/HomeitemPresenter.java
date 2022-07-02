package com.trademax.Activities.HomeItemsActivity.presenter;

import android.app.Activity;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trademax.Activities.HomeItemsActivity.HomeItemsActivity;
import com.trademax.Activities.HomeItemsActivity.view.Homeitemview;

import com.trademax.Activities.MainActivity;
import com.trademax.Adapters.HomeSimilatItemsAdapter;
import com.trademax.Handler.AddToCartHandler;
import com.trademax.Handler.PainterPlasterLikeProductHandler;
import com.trademax.Handler.SimilarproductHandler;
import com.trademax.Models.AddToCart.AddToCartExample;
import com.trademax.Models.PainterPlasterLikeProduct.PainterPlasterLikeProductExample;
import com.trademax.Models.SimilarProduct.SimilarProductExample;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;
import com.google.gson.Gson;

public class HomeitemPresenter {
    private Homeitemview homeitemview;
    private Activity activity;
    private RecyclerView recyclerView;

    public HomeitemPresenter(Homeitemview homeitemview, Activity activity, RecyclerView recyclerView) {
        this.homeitemview = homeitemview;
        this.activity = activity;
        this.recyclerView = recyclerView;
    }

    public void getAddtoCart(String productId, String product_price, String valueid, String quantity) {
        String userId = CSPreferences.readString(activity, Utils.USERID);
        if (userId == "") {
            userId = null;
            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);

        } else {

            String res = String.valueOf(Float.valueOf(product_price) * Float.valueOf(quantity));
            
//        int a = Integer.parseInt(product_price);
//        int b = Integer.parseInt(quantity);

//        int res = a * b;
            homeitemview.showDialog(activity);
            WebServices.getmInstance().getaddtocart(userId, productId,
                    quantity, res, valueid,
                    "Cart", new AddToCartHandler() {
                        @Override
                        public void onSuccess(AddToCartExample addtocartexample) {
                            homeitemview.hideDialog();
                            if (addtocartexample != null) {
                                if (addtocartexample.getIsSuccess() == true) {
//                                    homeitemview.showMessage(activity, addtocartexample.getMessage());

                                    homeitemview.showMessage(activity,"Add Item Cart Successfull");
                                } else {
                                    homeitemview.showMessage(activity, addtocartexample.getMessage());
                                }
                            } else {
                                homeitemview.showMessage(activity, addtocartexample.getMessage());
                            }
                        }

                        @Override
                        public void onError(String message) {
                            homeitemview.hideDialog();
                            homeitemview.showMessage(activity, message);
                        }
                    });

        }
    }

    public void HomeLikeMeathod(String productId) {
        //String productid = CSPreferences.readString(activity, Utils.TOKEN);
        String userId = CSPreferences.readString(activity, Utils.USERID);
        if (userId == "") {
            userId = null;
        }
        homeitemview.showDialog(activity);
        WebServices.getmInstance().getlikeProductMethod(userId, productId, new PainterPlasterLikeProductHandler() {
            @Override
            public void onSuccess(PainterPlasterLikeProductExample painterPlasterLikeProductExample) {
                homeitemview.hideDialog();
                if (painterPlasterLikeProductExample != null) {
                    if (painterPlasterLikeProductExample.getIsSuccess() == true) {
                        homeitemview.showMessage(activity, painterPlasterLikeProductExample.getMessage());
                        homeitemview.changeFavouriteImage(painterPlasterLikeProductExample.getMessage());


                    } else {
                        homeitemview.showMessage(activity, painterPlasterLikeProductExample.getMessage());
                    }
                }
            }

            @Override
            public void onError(String message) {
                homeitemview.hideDialog();
                homeitemview.showMessage(activity, message);
            }
        });

    }

    public void getSimilarProduct(String categori_id, String productId) {
        homeitemview.showDialog(activity);
        WebServices.getmInstance().getsimilarproduct(categori_id, productId, new SimilarproductHandler() {
            @Override
            public void onSuccess(SimilarProductExample similarProductExample) {
                homeitemview.hideDialog();
                if (similarProductExample != null) {
                    if (similarProductExample.getIsSuccess() == true) {
//                                homeitemview.showMessage(activity, similarProductExample.getMessage());
                        // addData(similarProductExample.getItems());
                        HomeSimilatItemsAdapter homeSimilatItemsAdapter = new HomeSimilatItemsAdapter(similarProductExample.getItems(), activity);
                        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
                        recyclerView.setAdapter(homeSimilatItemsAdapter);
                        homeSimilatItemsAdapter.ViewClickListner(new HomeSimilatItemsAdapter.OnViewClick() {
                            @Override
                            public void onlikeClick(int position) {
                                String data = new Gson().toJson(similarProductExample.getItems().get(position));
                                Intent intent = new Intent(activity, HomeItemsActivity.class);
                                intent.putExtra("arraylistData", data);
                                activity.startActivity(intent);

                            }
                        });

                    } else {
                        homeitemview.showMessage(activity, similarProductExample.getMessage());
                    }
                } else {
                    homeitemview.showMessage(activity, similarProductExample.getMessage());
                }
            }

            @Override
            public void onError(String message) {
                homeitemview.hideDialog();
                homeitemview.showMessage(activity, message);
            }
        });

    }

}
