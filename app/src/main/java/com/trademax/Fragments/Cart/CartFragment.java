package com.trademax.Fragments.Cart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trademax.Activities.Checkout.CheckOutActivity;
import com.trademax.Fragments.Cart.presenter.CartPresenter;
import com.trademax.Fragments.Cart.view.Cartview;
import com.trademax.Models.CartListModel.CartlistData;
import com.trademax.R;
import com.trademax.Utils.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;

public class CartFragment extends Fragment implements View.OnClickListener, Cartview {
    private static final String TAG = "CartFragment";
    private Activity activity;
    private View view;
    private RecyclerView recyclerView;
    private Button btn_checkout;
    private CartPresenter cartPresenter;
    private TextView tv_totalPrice;
    private LinearLayout linear_dataDetails;
    private LinearLayout linear_emptyCart;
    private ArrayList<CartlistData> cartData;
    private int sum = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        activity = getActivity();
        init();
        listiners();

        cartPresenter = new CartPresenter(activity, this, recyclerView, linear_dataDetails, linear_emptyCart);
        cartPresenter.getCartlist();
        return view;
    }

    private void init() {
        btn_checkout = view.findViewById(R.id.btn_checkout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_mybagItems);
        tv_totalPrice = (TextView) view.findViewById(R.id.tv_totalPrice);
        linear_dataDetails = (LinearLayout) view.findViewById(R.id.linear_dataDetails);
        linear_emptyCart = (LinearLayout) view.findViewById(R.id.linear_emptyCart);

    }

    private void listiners() {
        btn_checkout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btn_checkout) {
            String data = new Gson().toJson(cartData);
            Intent intent = new Intent(activity, CheckOutActivity.class);
            intent.putExtra("arraylistData", data);
            intent.putExtra("price", tv_totalPrice.getText().toString());
            activity.startActivity(intent);
            Log.e("onClick", tv_totalPrice.getText().toString());
        }
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

    @Override
    public void setData(ArrayList<CartlistData> data) {
        this.cartData = data;
    }

    @Override
    public void settotal(ArrayList<CartlistData> data) {
//        for (int i = 0; i < data.size(); i++) {
//            sum += data.get(i);
//        }
        float sum = 0;
        for (int i = 0; i < data.size(); i++) {
            sum = sum + data.get(i).getTotal();
        }

        tv_totalPrice.setText("" + sum);

    }
}