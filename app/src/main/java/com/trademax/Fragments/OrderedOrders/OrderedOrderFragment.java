package com.trademax.Fragments.OrderedOrders;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.trademax.Fragments.OrderedOrders.presenter.OrderedOrderpresenter;
import com.trademax.Fragments.OrderedOrders.view.OrderedOrderview;
import com.trademax.R;
import com.trademax.Utils.Utils;


public class OrderedOrderFragment extends Fragment implements OrderedOrderview {

    private Activity activity;
    private View view;
    private RecyclerView recycler_orderedOrder;

    private OrderedOrderpresenter orderedOrderpresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ordered_order, container, false);
        activity = getActivity();

        init();
        listiners();

        orderedOrderpresenter = new OrderedOrderpresenter(activity,this,recycler_orderedOrder);
        orderedOrderpresenter.getmyOrders();
        return view;
    }

    private void init() {

        recycler_orderedOrder = view.findViewById(R.id.recycler_orderedOrder);

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