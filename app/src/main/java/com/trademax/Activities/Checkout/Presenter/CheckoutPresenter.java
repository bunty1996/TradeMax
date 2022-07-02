package com.trademax.Activities.Checkout.Presenter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.trademax.Activities.Checkout.view.Checkoutview;
import com.trademax.Activities.SuccessScreen.SuccessActivity;
import com.trademax.Handler.CheckoutHandler;
import com.trademax.Models.CartListModel.CartlistData;
import com.trademax.Models.CheckoutModel.CheckoutExample;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class CheckoutPresenter {
    private static final String TAG = "CheckoutPresenter";
    private static Checkoutview checkoutview;
    private static Activity activity;
    private String jsonString;
    private TextView tv_totalprice;

    public CheckoutPresenter(Checkoutview checkoutview, Activity activity) {
        this.checkoutview = checkoutview;
        this.activity = activity;
    }

    public static void getcheckout(ArrayList<CartlistData> cartlistData, TextView tv_totalprice, String addressid) {
//        this.tv_totalprice = tv_totalprice;
        String userId = CSPreferences.readString(activity, Utils.USERID);
        if (userId == ""){
            userId = null;
        }
        JsonArray jsonArray = new JsonArray();
        try {
            for (CartlistData cart : cartlistData) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("cartId", cart.getId());
                jsonArray.add(jsonObject);
            }
            Log.e("JSONArray", String.valueOf(jsonArray));
        } catch (Exception jse) {
            jse.printStackTrace();
        }

        checkoutview.showDialog(activity);
        WebServices.getmInstance().getcheckout(userId, tv_totalprice.getText().toString(), "0", addressid, jsonArray, new CheckoutHandler() {
            @Override
            public void onSuccess(CheckoutExample checkoutExample) {
                checkoutview.hideDialog();
                if (checkoutExample != null) {
                    if (checkoutExample.getIsSuccess() == true) {
                        checkoutview.showMessage(activity, checkoutExample.getMessage());

                        Intent intent = new Intent(activity, SuccessActivity.class);
                        activity.startActivity(intent);
                        activity.finish();

                    } else {
                        checkoutview.showMessage(activity, checkoutExample.getMessage());
                    }
                } else {
                    checkoutview.showMessage(activity, checkoutExample.getMessage());
                }
            }

            @Override
            public void onError(String message) {
                checkoutview.hideDialog();
                checkoutview.showMessage(activity, message);
            }
        });

    }
}
