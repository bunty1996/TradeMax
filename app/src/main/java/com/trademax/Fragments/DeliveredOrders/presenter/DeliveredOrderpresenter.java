package com.trademax.Fragments.DeliveredOrders.presenter;

import android.app.Activity;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trademax.Adapters.MyOrderDeliveredDetailsAdapter;
import com.trademax.Adapters.MyOrderDetailsAdapter;
import com.trademax.Fragments.DeliveredOrders.view.DeliveredOrderview;
import com.trademax.Handler.MyOrderHandler;
import com.trademax.Models.MyOrder.MyOrderDatum;
import com.trademax.Models.MyOrder.MyOrderExample;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;

import java.util.ArrayList;
import java.util.List;

public class DeliveredOrderpresenter {

    private Activity activity;
    private DeliveredOrderview deliveredOrderview;
    private RecyclerView recycler_deliveredOrder;

    public DeliveredOrderpresenter(Activity activity, DeliveredOrderview deliveredOrderview, RecyclerView recycler_deliveredOrder) {
        this.activity = activity;
        this.deliveredOrderview = deliveredOrderview;
        this.recycler_deliveredOrder = recycler_deliveredOrder;
    }


    public void getmyOrders() {
        String userId = CSPreferences.readString(activity, Utils.USERID);
        if (userId == ""){
            userId = null;
        }
        deliveredOrderview.showDialog(activity);
        WebServices.getmInstance().getmyorders(userId, new MyOrderHandler() {
            @Override
            public void onSuccess(MyOrderExample example) {
                deliveredOrderview.hideDialog();
                if (example != null) {
                    if (example.getIsSuccess() == true) {


                        List<MyOrderDatum> deliverlist = new ArrayList<>();
                        deliverlist.clear();

//                        deliveredOrderview.showMessage(activity, example.getMessage());
                        for (int i = 0; i < example.getData().size(); i++) {
                            if (example.getData().get(i).getStatus().equalsIgnoreCase("Delivered")) {

                                deliverlist.add(example.getData().get(i));
                                addData(deliverlist, i);
                            }
                        }
                    } else {
                        deliveredOrderview.showMessage(activity, example.getMessage());
                    }
                }
            }

            @Override
            public void onError(String message) {
                deliveredOrderview.hideDialog();
                deliveredOrderview.showMessage(activity, message);
            }
        });

    }

    private void addData(List<MyOrderDatum> data, int position) {
        MyOrderDeliveredDetailsAdapter myOrderDetailsAdapter = new MyOrderDeliveredDetailsAdapter(data, activity, "3", position);
        recycler_deliveredOrder.setHasFixedSize(true);
        recycler_deliveredOrder.setLayoutManager(new LinearLayoutManager(activity));
        recycler_deliveredOrder.setAdapter(myOrderDetailsAdapter);

    }
}
