package com.trademax.Activities.Painters_Plasters.presenter;

import android.app.Activity;
import android.widget.GridView;
import android.widget.ImageView;

import com.trademax.Activities.Painters_Plasters.view.Painter_PlastersView;
import com.trademax.Adapters.Painter_PlasterGridAdapter;
import com.trademax.Adapters.SearchGridAdapter;
import com.trademax.Handler.GetProductBySubCategoriesHandler;
import com.trademax.Handler.PainterPlasterLikeProductHandler;
import com.trademax.Handler.SearchHandler;
import com.trademax.Models.PainterPlasterLikeProduct.PainterPlasterLikeProductExample;
import com.trademax.Models.ProductBySubCategories.ProductBySubCatExample;
import com.trademax.Models.ProductBySubCategories.ProductBySubCatItem;
import com.trademax.Models.SearchModel.SearchDatum;
import com.trademax.Models.SearchModel.SearchExample;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;

import java.util.ArrayList;

public class Painter_PlastersPresenter {

    private Activity activity;
    private Painter_PlastersView painter_plastersView;
    private final GridView grid_painter_plaster;
    private String subcatId;

    public Painter_PlastersPresenter(Activity activity, Painter_PlastersView painter_plastersView, GridView grid_painter_plaster, String subcatId) {
        this.activity = activity;
        this.painter_plastersView = painter_plastersView;
        this.grid_painter_plaster = grid_painter_plaster;
        this.subcatId = subcatId;
    }

    public void getproductBySubCategoriesMethod(String Subcategoriid) {

        String productid = CSPreferences.readString(activity, Utils.TOKEN);

        String userId = CSPreferences.readString(activity, Utils.USERID);
        if (userId == "") {
            userId = null;
        }

        painter_plastersView.showDialog(activity);
        WebServices.getmInstance().getproductBySubCategoriesMethod(userId, 1, 100, subcatId, new GetProductBySubCategoriesHandler() {
            @Override
            public void onSuccess(ProductBySubCatExample productBySubCatExample) {
                painter_plastersView.hideDialog();
                if (productBySubCatExample != null) {
                    if (productBySubCatExample.getIsSuccess() == true) {
//                        painter_plastersView.showMessage(activity, productBySubCatExample.getMessage());
                        updateAdapter(productBySubCatExample.getItems());
                    } else {
                        painter_plastersView.showMessage(activity, productBySubCatExample.getMessage());
                    }
                }
            }

            @Override
            public void onError(String message) {
                painter_plastersView.hideDialog();
                painter_plastersView.showMessage(activity, message);
            }
        });

    }

    public void getSortproductBySubCategoriesMethod(int pageno, int pagesize, String subcat, String pricesort) {

        String userId = CSPreferences.readString(activity, Utils.USERID);
        if (userId == "") {
            userId = null;
        }

        //   painter_plastersView.showDialog(activity);
        WebServices.getmInstance().getsortproductBySubCategoriesMethod(userId, pageno, pagesize, subcatId, pricesort, new GetProductBySubCategoriesHandler() {
            @Override
            public void onSuccess(ProductBySubCatExample productBySubCatExample) {
                painter_plastersView.hideDialog();
                if (productBySubCatExample != null) {
                    if (productBySubCatExample.getIsSuccess() == true) {
                        updateAdapter(productBySubCatExample.getItems());

                    } else {
                        painter_plastersView.showMessage(activity, productBySubCatExample.getMessage());
                    }
                }
            }

            @Override
            public void onError(String message) {
                painter_plastersView.hideDialog();
                painter_plastersView.showMessage(activity, message);
            }
        });

    }

    public void PainterPlasterLikeProductMeathod(String productId) {

        //String productid = CSPreferences.readString(activity, Utils.TOKEN);
        String userId = CSPreferences.readString(activity, Utils.USERID);
        if (userId == "") {
            userId = null;
        }

        painter_plastersView.showDialog(activity);
        WebServices.getmInstance().getlikeProductMethod(userId, productId, new PainterPlasterLikeProductHandler() {
            @Override
            public void onSuccess(PainterPlasterLikeProductExample painterPlasterLikeProductExample) {
                painter_plastersView.hideDialog();
                if (painterPlasterLikeProductExample != null) {
                    if (painterPlasterLikeProductExample.getIsSuccess() == true) {
                        painter_plastersView.showMessage(activity, painterPlasterLikeProductExample.getMessage());
                        getproductBySubCategoriesMethod(subcatId);
                    } else {
                        painter_plastersView.showMessage(activity, painterPlasterLikeProductExample.getMessage());
                    }
                }
            }

            @Override
            public void onError(String message) {
                painter_plastersView.hideDialog();
                painter_plastersView.showMessage(activity, message);
            }
        });

    }

    public void getSearchMethod(String search) {

        String Token = CSPreferences.readString(activity, Utils.TOKEN);

        painter_plastersView.showDialog(activity);
        WebServices.getmInstance().getsearchproducts(search, "product", Token, new SearchHandler() {
            @Override
            public void onSuccess(SearchExample searchExample) {
                painter_plastersView.hideDialog();
                if (searchExample != null) {
                    if (searchExample.getIsSuccess() == true) {
                        painter_plastersView.showMessage(activity, searchExample.getMessage());
                        updateSearchAdapter((ArrayList<SearchDatum>) searchExample.getData());

                    } else {
                        painter_plastersView.showMessage(activity, searchExample.getMessage());
                    }
                } else {
                    painter_plastersView.showMessage(activity, searchExample.getMessage());

                }
            }

            @Override
            public void onError(String message) {
                painter_plastersView.hideDialog();
                painter_plastersView.showMessage(activity, message);
            }
        });

    }


    private void updateAdapter(ArrayList<ProductBySubCatItem> productBySubCatItemList) {
        Painter_PlasterGridAdapter painter_plasterGridAdapter = new Painter_PlasterGridAdapter(activity, productBySubCatItemList);
        grid_painter_plaster.setAdapter(painter_plasterGridAdapter);
        painter_plasterGridAdapter.ViewClickListner(new Painter_PlasterGridAdapter.OnViewClick() {
            @Override
            public void onlikeClick(int position, ImageView imglike, String productId) {
                PainterPlasterLikeProductMeathod(productId);
            }
        });
    }

    private void updateSearchAdapter(ArrayList<SearchDatum> searchData) {
        SearchGridAdapter searchGridAdapter = new SearchGridAdapter(activity, searchData);
        grid_painter_plaster.setAdapter(searchGridAdapter);
        searchGridAdapter.ViewClickListner(new SearchGridAdapter.OnViewClick() {
            @Override
            public void onlikeClick(int position, ImageView imglike, String productId) {
                PainterPlasterLikeProductMeathod(productId);
            }
        });

    }
}
