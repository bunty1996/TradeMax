package com.trademax.Fragments.ShippingOrders;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trademax.Fragments.ShippingOrders.presenter.ShippingOrderpresenter;
import com.trademax.Fragments.ShippingOrders.view.ShippingOrderview;
import com.trademax.R;
import com.trademax.Utils.Utils;


public class ShippingOrderFragment extends Fragment implements ShippingOrderview {

    private Activity activity;
    private View view;
    private RecyclerView recycler_shippingOrder;
    private ShippingOrderpresenter shippingOrderpresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_shipping_order, container, false);
        activity = getActivity();

        init();
        listiners();

        shippingOrderpresenter = new ShippingOrderpresenter(activity,  this, recycler_shippingOrder);
        shippingOrderpresenter.getmyOrders();

        return view;
    }

    private void init() {

        recycler_shippingOrder = view.findViewById(R.id.recycler_shippingOrder);

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