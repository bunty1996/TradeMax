package com.trademax.Activities.FavouriteItem.presenter;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trademax.Activities.FavouriteItem.view.Favitemview;
import com.trademax.Adapters.HomeSimilatItemsAdapter;
import com.trademax.Handler.AddToCartHandler;
import com.trademax.Handler.PainterPlasterLikeProductHandler;
import com.trademax.Handler.SimilarproductHandler;
import com.trademax.Models.AddToCart.AddToCartExample;
import com.trademax.Models.PainterPlasterLikeProduct.PainterPlasterLikeProductExample;
import com.trademax.Models.SimilarProduct.SimilarProductExample;
import com.trademax.Models.SimilarProduct.SimilarProductItem;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;

import java.util.List;

public class Favitempresenter {

    private Favitemview favitemview;
    private Activity activity;
    private RecyclerView recyclerView;


    public Favitempresenter(Favitemview favitemview, Activity activity, RecyclerView recyclerView) {
        this.favitemview = favitemview;
        this.activity = activity;
        this.recyclerView = recyclerView;
    }

    public void getAddtoCart(String productId, String product_price , String valueid, String quantity) {
        String userId = CSPreferences.readString(activity, Utils.USERID);
        if (userId == ""){
            userId = null;
        }
        favitemview.showDialog(activity);
        WebServices.getmInstance().getaddtocart(userId, productId,
                quantity,product_price,valueid,
                "Cart", new AddToCartHandler() {
                    @Override
                    public void onSuccess(AddToCartExample addtocartexample) {
                        favitemview.hideDialog();
                        if (addtocartexample != null) {
                            if (addtocartexample.getIsSuccess() == true) {
//                                favitemview.showMessage(activity, addtocartexample.getMessage());
                                favitemview.showMessage(activity,"Add Item Cart Successfull");
                            } else {
                                favitemview.showMessage(activity, addtocartexample.getMessage());
                            }
                        } else {
                            favitemview.showMessage(activity, addtocartexample.getMessage());
                        }
                    }
                    @Override
                    public void onError(String message) {
                        favitemview.hideDialog();
                        favitemview.showMessage(activity, message);
                    }
                });

    }
    public void HomeLikeMeathod(String productId) {
        String userId = CSPreferences.readString(activity, Utils.USERID);
        if (userId == ""){
            userId = null;
        }
        favitemview.showDialog(activity);
        WebServices.getmInstance().getlikeProductMethod(userId, productId, new PainterPlasterLikeProductHandler() {
            @Override
            public void onSuccess(PainterPlasterLikeProductExample painterPlasterLikeProductExample) {
                favitemview.hideDialog();
                if (painterPlasterLikeProductExample != null) {
                    if (painterPlasterLikeProductExample.getIsSuccess() == true) {
                        favitemview.showMessage(activity, painterPlasterLikeProductExample.getMessage());
                        favitemview.changeFavouriteImage(painterPlasterLikeProductExample.getMessage());

                    } else {
                        favitemview.showMessage(activity, painterPlasterLikeProductExample.getMessage());
                    }
                }
            }

            @Override
            public void onError(String message) {
                favitemview.hideDialog();
                favitemview.showMessage(activity, message);
            }
        });

    }

    public void getSimilarProduct(String categori_id, String productId) {
        favitemview.showDialog(activity);
        WebServices.getmInstance().getsimilarproduct(categori_id, productId, new SimilarproductHandler() {
            @Override
            public void onSuccess(SimilarProductExample similarProductExample) {
                favitemview.hideDialog();
                if (similarProductExample != null) {
                    if (similarProductExample.getIsSuccess() == true) {
//                                homeitemview.showMessage(activity, similarProductExample.getMessage());
                        addData(similarProductExample.getItems());

                    } else {
                        favitemview.showMessage(activity, similarProductExample.getMessage());
                    }
                } else {
                    favitemview.showMessage(activity, similarProductExample.getMessage());
                }
            }
            @Override
            public void onError(String message) {
                favitemview.hideDialog();
                favitemview.showMessage(activity, message);
            }
        });

    }

    private void addData(List<SimilarProductItem> similarProductItems){
        HomeSimilatItemsAdapter homeSimilatItemsAdapter = new HomeSimilatItemsAdapter(similarProductItems, activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(homeSimilatItemsAdapter);

    }

}
