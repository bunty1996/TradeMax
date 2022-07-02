package com.trademax.Activities.Builder.presenter;

import android.app.Activity;
import android.widget.GridView;
import android.widget.ImageView;

import com.trademax.Activities.Builder.view.BuilderView;
import com.trademax.Adapters.BuilderGridAdapter;
import com.trademax.Handler.GetProductBySubCategoriesHandler;
import com.trademax.Handler.PainterPlasterLikeProductHandler;
import com.trademax.Models.PainterPlasterLikeProduct.PainterPlasterLikeProductExample;
import com.trademax.Models.ProductBySubCategories.ProductBySubCatExample;
import com.trademax.Models.ProductBySubCategories.ProductBySubCatItem;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;

import java.util.ArrayList;

public class BuilderPresenter {

    private GridView grid_builder;
    private Activity activity;
    private BuilderView builderView;
    private String subcatId;


    public BuilderPresenter(Activity activity, BuilderView builderView, GridView grid_builder, String subcatId) {
        this.activity = activity;
        this.builderView = builderView;
        this.grid_builder = grid_builder;
        this.subcatId = subcatId;

    }

    public void getproductBySubCategoriesMethod(String Subcategoriid) {

        String userId = CSPreferences.readString(activity, Utils.USERID);
        if (userId == ""){
            userId = null;
        }
//        builderView.showDialog(activity);
        WebServices.getmInstance().getproductBySubCategoriesMethod(userId, 1, 100, Subcategoriid, new GetProductBySubCategoriesHandler() {
            @Override
            public void onSuccess(ProductBySubCatExample productBySubCatExample) {
//                builderView.hideDialog();
                if (productBySubCatExample != null) {
                    if (productBySubCatExample.getIsSuccess() == true) {
                        updateAdapter(productBySubCatExample.getItems());

                    } else {
                        builderView.showMessage(activity, productBySubCatExample.getMessage());
                    }
                }
            }
            @Override
            public void onError(String message) {
//                builderView.hideDialog();
                builderView.showMessage(activity, message);
            }
        });

    }

    public void getSortproductBySubCategoriesMethod(int pageno, int pagesize, String subcat, String pricesort) {

        String userId = CSPreferences.readString(activity, Utils.USERID);
        if (userId == ""){
            userId = null;
        }
        builderView.showDialog(activity);
        WebServices.getmInstance().getsortproductBySubCategoriesMethod(userId, pageno, pagesize, subcatId, pricesort, new GetProductBySubCategoriesHandler() {
            @Override
            public void onSuccess(ProductBySubCatExample productBySubCatExample) {
                builderView.hideDialog();
                if (productBySubCatExample != null) {
                    if (productBySubCatExample.getIsSuccess() == true) {
                        updateAdapter(productBySubCatExample.getItems());

                    } else {
                        builderView.showMessage(activity, productBySubCatExample.getMessage());
                    }
                }
            }

            @Override
            public void onError(String message) {
                builderView.hideDialog();
                builderView.showMessage(activity, message);
            }
        });

    }

    public void PainterPlasterLikeProductMeathod(String productId) {
        String userId = CSPreferences.readString(activity, Utils.USERID);

        if (userId == ""){
            userId = null;
        }
        builderView.showDialog(activity);
        WebServices.getmInstance().getlikeProductMethod(userId, productId, new PainterPlasterLikeProductHandler() {
            @Override
            public void onSuccess(PainterPlasterLikeProductExample painterPlasterLikeProductExample) {
                builderView.hideDialog();
                if (painterPlasterLikeProductExample != null) {
                    if (painterPlasterLikeProductExample.getIsSuccess() == true) {
                        builderView.showMessage(activity, painterPlasterLikeProductExample.getMessage());
                        getproductBySubCategoriesMethod(subcatId);
                    } else {
                        builderView.showMessage(activity, painterPlasterLikeProductExample.getMessage());
                    }
                }
            }

            @Override
            public void onError(String message) {
                builderView.hideDialog();
                builderView.showMessage(activity, message);
            }
        });

    }

    private void updateAdapter(ArrayList<ProductBySubCatItem> productBySubCatItems) {
        BuilderGridAdapter builderGridAdapter = new BuilderGridAdapter(activity, productBySubCatItems);
        grid_builder.setAdapter(builderGridAdapter);
        builderGridAdapter.ViewClickListner(new BuilderGridAdapter.OnViewClick() {
            @Override
            public void onlikeClick(int position, ImageView imglike, String productId) {

                PainterPlasterLikeProductMeathod(productId);
            }
        });

    }

}
