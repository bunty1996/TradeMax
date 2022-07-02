package com.trademax.Fragments.OrderedOrders.presenter;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trademax.Adapters.MyOrderDetailsAdapter;
import com.trademax.Adapters.MyOrderedOrderDetailsAdapter;
import com.trademax.Fragments.OrderedOrders.view.OrderedOrderview;
import com.trademax.Handler.MyOrderHandler;
import com.trademax.Models.MyOrder.MyOrderDatum;
import com.trademax.Models.MyOrder.MyOrderExample;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;

import java.util.ArrayList;
import java.util.List;

public class OrderedOrderpresenter {

    private final RecyclerView recycler_orderedOrder;
    private Activity activity;
    private OrderedOrderview orderedOrderview;

    public OrderedOrderpresenter(Activity activity, OrderedOrderview orderedOrderview, RecyclerView recycler_orderedOrder) {
        this.activity = activity;
        this.orderedOrderview = orderedOrderview;
        this.recycler_orderedOrder = recycler_orderedOrder;

    }

    public void getmyOrders() {
        String userId = CSPreferences.readString(activity, Utils.USERID);
        if (userId == ""){
            userId = null;
        }
        orderedOrderview.showDialog(activity);
        WebServices.getmInstance().getmyorders(userId, new MyOrderHandler() {
            @Override
            public void onSuccess(MyOrderExample example) {
                orderedOrderview.hideDialog();
                if (example != null) {
                    if (example.getIsSuccess() == true) {

                        List<MyOrderDatum> orderedlist = new ArrayList<>();
                        orderedlist.clear();
//                        orderedOrderview.showMessage(activity, example.getMessage());
                        for (int i = 0; i < example.getData().size(); i++) {
                            if (example.getData().get(i).getStatus().equalsIgnoreCase("Ordered")) {

                                orderedlist.add(example.getData().get(i));
                                addData(orderedlist, i);
                            }
                        }
                    } else {
                        orderedOrderview.showMessage(activity, example.getMessage());
                    }
                }
            }

            @Override
            public void onError(String message) {
                orderedOrderview.hideDialog();
                orderedOrderview.showMessage(activity, message);
            }
        });
    }

    private void addData(List<MyOrderDatum> data, int position) {
        MyOrderedOrderDetailsAdapter orderedDetailsAdapter = new MyOrderedOrderDetailsAdapter(data, activity, "1", position);
        recycler_orderedOrder.setHasFixedSize(true);
        recycler_orderedOrder.setLayoutManager(new LinearLayoutManager(activity));
        recycler_orderedOrder.setAdapter(orderedDetailsAdapter);

    }
}
