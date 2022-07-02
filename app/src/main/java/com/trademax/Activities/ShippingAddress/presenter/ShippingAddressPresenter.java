package com.trademax.Activities.ShippingAddress.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.CheckBox;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trademax.Activities.ShippingAddress.view.ShippingAddressView;
import com.trademax.Adapters.ShippingAddressAdapter;
import com.trademax.Handler.GetAddressHandler;
import com.trademax.Models.GetAddress.GetAddressExample;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;

public class ShippingAddressPresenter {

    private ShippingAddressView shippingAddressView;
    private Activity activity;
    private RecyclerView recyclerAddAddress;


    public ShippingAddressPresenter(Activity activity, ShippingAddressView shippingAddressView) {
        this.activity = activity;
        this.shippingAddressView = shippingAddressView;
    }

    public void getShippingAddress(RecyclerView recycler_addAddress) {

        this.recyclerAddAddress = recycler_addAddress;

        String userId = CSPreferences.readString(activity, Utils.USERID);
        if (userId == ""){
            userId = null;
        }
        // empty address id//
        shippingAddressView.showDialog(activity);
        WebServices.getmInstance().getAddressDetailsMethod(userId,"" , new GetAddressHandler() {
            @Override
            public void onSuccess(GetAddressExample getAddressExample) {
                shippingAddressView.hideDialog();
                if (getAddressExample != null) {
                    if (getAddressExample.getIsSuccess() == true) {
//                        shippingAddressView.showMessage(activity, getAddressExample.getMessage());
                        ShippingAddressAdapter shippingAddressAdapter = new ShippingAddressAdapter(getAddressExample.getData(), activity);
                        recyclerAddAddress.setHasFixedSize(true);
                        recyclerAddAddress.setLayoutManager(new LinearLayoutManager(activity));
                        recyclerAddAddress.setAdapter(shippingAddressAdapter);

                    } else {
                        shippingAddressView.showMessage(activity, getAddressExample.getMessage());
                    }
                } else {
                    shippingAddressView.showMessage(activity, getAddressExample.getMessage());
                }
            }

            @Override
            public void onError(String message) {
                shippingAddressView.hideDialog();
                shippingAddressView.showMessage(activity, message);
            }
        });

    }
}
