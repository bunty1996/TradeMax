package com.trademax.Fragments.AllOrders;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trademax.Fragments.AllOrders.presenter.AllOrderpresenter;
import com.trademax.Fragments.AllOrders.view.AllOrderview;
import com.trademax.R;
import com.trademax.Utils.Utils;

public class AllOrderFragment extends Fragment implements AllOrderview {

    private Activity activity;
    private View view;
    private RecyclerView recycler_allOrder;
    private AllOrderpresenter allOrderpresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_all_order, container, false);
        activity = getActivity();
        init();
        litiners();

        allOrderpresenter = new AllOrderpresenter(activity, this, recycler_allOrder);
        allOrderpresenter.getmyOrders();

        return view;
    }

    private void litiners() {

    }

    private void init() {
        recycler_allOrder = view.findViewById(R.id.recycler_allOrder);

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