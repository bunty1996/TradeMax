package com.trademax.Fragments.DeliveredOrders;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trademax.Fragments.DeliveredOrders.presenter.DeliveredOrderpresenter;
import com.trademax.Fragments.DeliveredOrders.view.DeliveredOrderview;
import com.trademax.R;
import com.trademax.Utils.Utils;


public class DeliveredOrderFragment extends Fragment implements DeliveredOrderview {

    private Activity activity;
    private View view;
    private RecyclerView recycler_deliveredOrder;
    private DeliveredOrderpresenter deliveredOrderpresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_delivered_order, container, false);
        activity = getActivity();

        init();
        listiners();

        deliveredOrderpresenter = new DeliveredOrderpresenter(activity, this, recycler_deliveredOrder);
        deliveredOrderpresenter.getmyOrders();

        return view;
    }

    private void init() {

        recycler_deliveredOrder = view.findViewById(R.id.recycler_deliveredOrder);

    }

    private void listiners() {

    }

    @Override
    public void showDialog(Activity activity) {
        Utils.showDialogMethod(activity, activity.getFragmentManager());
    }

    @Override
    public void hideDialog() {
        Utils.hideDialog();
    }

    @Override
    public void showMessage(Activity activity, String message) {
        Utils.showMessage(activity, message);
    }

}