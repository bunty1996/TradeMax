package com.trademax.Fragments.ShippingOrders.presenter;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trademax.Adapters.MyOrderDetailsAdapter;
import com.trademax.Adapters.MyOrderShippedDetailsAdapter;
import com.trademax.Fragments.ShippingOrders.view.ShippingOrderview;
import com.trademax.Handler.MyOrderHandler;
import com.trademax.Models.MyOrder.MyOrderDatum;
import com.trademax.Models.MyOrder.MyOrderExample;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;

import java.util.ArrayList;
import java.util.List;

public class ShippingOrderpresenter {

    private RecyclerView recyclerView;
    private Activity activity;
    private ShippingOrderview orderview;


    public ShippingOrderpresenter(Activity activity, ShippingOrderview orderview, RecyclerView recyclerView) {
        this.activity = activity;
        this.orderview = orderview;
        this.recyclerView = recyclerView;
    }

    public void getmyOrders() {
        String userId = CSPreferences.readString(activity, Utils.USERID);
        if (userId == ""){
            userId = null;
        }
        orderview.showDialog(activity);
        WebServices.getmInstance().getmyorders(userId, new MyOrderHandler() {
            @Override
            public void onSuccess(MyOrderExample example) {
                orderview.hideDialog();
                if (example != null) {
                    if (example.getIsSuccess() == true) {

                        List<MyOrderDatum> shippinglist = new ArrayList<>();
                        shippinglist.clear();
//                        orderview.showMessage(activity, example.getMessage());
                        if (!(example.getData().size() == 0)) {
                            for (int i = 0; i < example.getData().size(); i++) {
                                if (example.getData().get(i).getStatus().equals("Shipping")) {

                                    shippinglist.add(example.getData().get(i));
                                    addData(shippinglist, i);
                                }
                            }
                        }
                    } else {
                        orderview.showMessage(activity, example.getMessage());
                    }
                }
            }

            @Override
            public void onError(String message) {
                orderview.hideDialog();
                orderview.showMessage(activity, message);
            }
        });

    }

    private void addData(List<MyOrderDatum> data, int position) {
        MyOrderShippedDetailsAdapter myOrderDetailsAdapter = new MyOrderShippedDetailsAdapter(data, activity, "2", position);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(myOrderDetailsAdapter);

    }
}
