package com.trademax.Fragments.AllOrders.presenter;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trademax.Adapters.MyOrderDetailsAdapter;
import com.trademax.Fragments.AllOrders.view.AllOrderview;
import com.trademax.Handler.MyOrderHandler;
import com.trademax.Models.MyOrder.MyOrderDatum;
import com.trademax.Models.MyOrder.MyOrderExample;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;

import java.util.List;

public class AllOrderpresenter {

    private final RecyclerView recycler_allOrder;
    private Activity activity;
    private AllOrderview allOrderview;
    private String userId;

    public AllOrderpresenter(Activity activity, AllOrderview allOrderview, RecyclerView recycler_allOrder) {
        this.activity = activity;
        this.allOrderview = allOrderview;
        this.recycler_allOrder = recycler_allOrder;

    }

    public void getmyOrders() {

        String userId = CSPreferences.readString(activity, Utils.USERID);

        if (userId == "") {
            userId = null;
        }
        allOrderview.showDialog(activity);
        WebServices.getmInstance().getmyorders(userId, new MyOrderHandler() {
            @Override
            public void onSuccess(MyOrderExample example) {
                allOrderview.hideDialog();
                if (example != null) {
                    if (example.getIsSuccess() == true) {
//                        allOrderview.showMessage(activity, example.getMessage());
//                        for (int i = 0; i < example.getData().get(i).getCart().size(); i++) {
//                            if(example.getData().get(i).getStatus().equalsIgnoreCase("Ordered")){
                        addData(example.getData());
//                            }
//                        }

                    } else {
                        allOrderview.showMessage(activity, example.getMessage());
                    }
                }
            }

            @Override
            public void onError(String message) {
                allOrderview.hideDialog();
                allOrderview.showMessage(activity, message);
            }
        });

    }

    private void addData(List<MyOrderDatum> data) {
        MyOrderDetailsAdapter allOrderDetailsAdapter = new MyOrderDetailsAdapter(data, activity, "");
        recycler_allOrder.setHasFixedSize(true);
        recycler_allOrder.setLayoutManager(new LinearLayoutManager(activity));
        recycler_allOrder.setAdapter(allOrderDetailsAdapter);

    }
}
