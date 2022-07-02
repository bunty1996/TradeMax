package com.trademax.Fragments.Cart.presenter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trademax.Adapters.MyBagAdapter;
import com.trademax.Fragments.Cart.CartFragment;
import com.trademax.Fragments.Cart.view.Cartview;
import com.trademax.Handler.CartListHandler;
import com.trademax.Handler.DeleteListHandler;
import com.trademax.Handler.PainterPlasterLikeProductHandler;
import com.trademax.Models.CartListModel.CartlistData;
import com.trademax.Models.CartListModel.CartlistExample;
import com.trademax.Models.DeleteList.DeleteExample;
import com.trademax.Models.PainterPlasterLikeProduct.PainterPlasterLikeProductExample;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;

import java.util.ArrayList;


public class CartPresenter {

    private Activity activity;
    private Cartview cartview;
    private RecyclerView recyclerView;
    private LinearLayout linear_dataDetails;
    private LinearLayout linear_emptyCart;

    public CartPresenter(Activity activity, Cartview cartview, RecyclerView recyclerView, LinearLayout linear_dataDetails, LinearLayout linear_emptyCart) {
        this.activity = activity;
        this.cartview = cartview;
        this.recyclerView = recyclerView;
        // this.tv_totalPrice = tv_totalPrice;
        this.linear_dataDetails = linear_dataDetails;
        this.linear_emptyCart = linear_emptyCart;
    }

    public void getCartlist() {
        cartview.showDialog(activity);
        String userId = CSPreferences.readString(activity, Utils.USERID);
        if (userId == "") {
            userId = null;
        }
        WebServices.getmInstance().getCartList(userId, new CartListHandler() {
            @Override
            public void onSuccess(CartlistExample cartlistExample) {
                cartview.hideDialog();
                if (cartlistExample != null) {
                    if (cartlistExample.getIsSuccess() == true) {
                        if (cartlistExample.getData().isEmpty()) {
                            linear_dataDetails.setVisibility(View.GONE);
                            linear_emptyCart.setVisibility(View.VISIBLE);
                        } else {
                            linear_dataDetails.setVisibility(View.VISIBLE);
                            linear_emptyCart.setVisibility(View.GONE);
                            addlist(cartlistExample.getData());
                        }
                    } else {
                        cartview.showMessage(activity, cartlistExample.getMessage());
                    }
                }
            }

            @Override
            public void onError(String message) {
                cartview.hideDialog();
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getDeletelist(String id) {
//        cartview.showDialog(activity);
        String TOKEN = CSPreferences.readString(activity, Utils.TOKEN);
        WebServices.getmInstance().getdelete(id, TOKEN, new DeleteListHandler() {
            @Override
            public void onSuccess(DeleteExample deleteExample) {
//                cartview.hideDialog();
                if (deleteExample != null) {
                    if (deleteExample.getIsSuccess() == true) {
                        cartview.showMessage(activity, deleteExample.getMessage());
                    } else {
                        cartview.showMessage(activity, deleteExample.getMessage());
                    }
                } else {
                    cartview.showMessage(activity, deleteExample.getMessage());
                }
            }

            @Override
            public void onError(String message) {
//                cartview.hideDialog();
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
            }
        });

    }

    //60a38d6a42d36a73c69f8eae
    public void HomeLikeMeathod(String productID) {
        String userId = CSPreferences.readString(activity, Utils.USERID);
        if (userId == "") {
            userId = null;
        }

        cartview.showDialog(activity);
        WebServices.getmInstance().getlikeProductMethod(userId, productID, new PainterPlasterLikeProductHandler() {
            @Override
            public void onSuccess(PainterPlasterLikeProductExample painterPlasterLikeProductExample) {
                cartview.hideDialog();
                if (painterPlasterLikeProductExample != null) {
                    if (painterPlasterLikeProductExample.getIsSuccess() == true) {
                        cartview.showMessage(activity, painterPlasterLikeProductExample.getMessage());
                    } else {
                        cartview.showMessage(activity, painterPlasterLikeProductExample.getMessage());
                    }
                }
            }

            @Override
            public void onError(String message) {
                cartview.hideDialog();
                cartview.showMessage(activity, message);
            }
        });

    }

    private void addlist(ArrayList<CartlistData> categoriesData) {
        MyBagAdapter myBagAdapter = new MyBagAdapter(categoriesData, activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(myBagAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        myBagAdapter.ViewClickListner(new MyBagAdapter.OnViewClick() {

            @Override
            public void ondeleteitem(int position, String id) {
                getDeletelist(categoriesData.get(position).getId());
                categoriesData.remove(position);
                myBagAdapter.notifyItemRemoved(position);
                Utils.changeFragment(activity, new CartFragment());

            }

            @Override
            public void onfav(int position) {
                String productID = categoriesData.get(position).getProduct().getId();
                HomeLikeMeathod(productID);
            }

            @Override
            public void ontotalPrice(ArrayList<CartlistData> total) {
                cartview.settotal(total);
                Log.e("priceTotal",  ""+total);
            }

            @Override
            public void getCartData(ArrayList<CartlistData> cartId) {
                cartview.setData(cartId);
            }
        });
    }


}
