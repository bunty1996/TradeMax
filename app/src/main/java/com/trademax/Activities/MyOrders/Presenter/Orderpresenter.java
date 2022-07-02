package com.trademax.Activities.MyOrders.Presenter;

import android.app.Activity;

import com.trademax.Activities.MyOrders.view.Orderview;
import com.trademax.Handler.MyOrderHandler;
import com.trademax.Models.MyOrder.MyOrderExample;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;
import com.google.android.material.tabs.TabLayout;

public class Orderpresenter {

    private final TabLayout tabLayout;
    private Activity activity;
    private Orderview orderview;


    public Orderpresenter(Activity activity, Orderview orderview, TabLayout tabLayout) {
        this.activity = activity;
        this.orderview = orderview;
        this.tabLayout = tabLayout;
    }


    public void getmyOrders() {
//        String userId = CSPreferences.readString(activity, Utils.USERID);
////        orderview.showDialog(activity);
//        WebServices.getmInstance().getmyorders(userId, new MyOrderHandler() {
//            @Override
//            public void onSuccess(MyOrderExample example) {
////                orderview.hideDialog();
//                if (example != null) {
//                    if (example.getIsSuccess() == true) {
//                        orderview.showMessage(activity, example.getMessage());
//
//                        tabLayout.addTab(tabLayout.newTab().setText("ALL"));
//                        tabLayout.addTab(tabLayout.newTab().setText("ORDERED"));
//                        tabLayout.addTab(tabLayout.newTab().setText("SHIPPING"));
//                        tabLayout.addTab(tabLayout.newTab().setText("DELIVERED"));
//                        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

                        orderview.setAdapter();
//                    } else {
////                        orderview.hideDialog();
//                        orderview.showMessage(activity, example.getMessage());
//                    }
//                }
//            }
//
//            @Override
//            public void onError(String message) {
////                orderview.hideDialog();
//                orderview.showMessage(activity, message);
//            }
//        });

    }

}
