package com.trademax.Activities.Checkout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stripe.android.PaymentConfiguration;
import com.trademax.Activities.AddCard.SelectCardActivity;
import com.trademax.Activities.AddNewCard.AddNewCardActivity;
import com.trademax.Activities.Builder.BuilderActivity;
import com.trademax.Activities.Checkout.Presenter.CheckoutPresenter;
import com.trademax.Activities.Checkout.view.Checkoutview;
import com.trademax.Activities.ShippingAddress.ShippingAddressActivity;
import com.trademax.Models.CartListModel.CartlistData;
import com.trademax.R;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CheckOutActivity extends AppCompatActivity implements View.OnClickListener, Checkoutview {
    private static final String TAG = "CheckOutActivity";
    private Activity activity;
    private ImageView tool_back;
    private TextView txt_ChangeAddress;
    private TextView txt_ChangeCard;
    private TextView tv_address;
    private TextView tv_order;
    private TextView tv_totalprice;
    private TextView tv_deliverycharges;
    private TextView tv_name;
    private LinearLayout li_orderSubmit;
    private Button place_order;
    private CheckoutPresenter presenter;
    private String name;
    private String address;
    private String city;
    private String dataString;
    private String pricestring;
    private int valueprice;
    private ArrayList<CartlistData> cartlistData;
    private String addressId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLightStatusBar(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_check_out);
        activity = this;
        init();
        listiners();

        Log.d(TAG, "onCreate" + pricestring);

        //String stringdouble= Double.toString(pricestring);
        dataString = getIntent().getStringExtra("arraylistData");
        cartlistData = new Gson().fromJson(dataString, new TypeToken<ArrayList<CartlistData>>() {
        }.getType());

        pricestring = getIntent().getStringExtra("price");

        presenter = new CheckoutPresenter(this, activity);
        name = CSPreferences.readString(activity, Utils.GETFULLADDRESS);
        address = CSPreferences.readString(activity, Utils.GETADDRESS);
        city = CSPreferences.readString(activity, Utils.GETCITY);
        addressId = CSPreferences.readString(activity, Utils.GETADDRESSID);
        tv_name.setText(name);
        tv_address.setText(address);
        tv_address.setText(address + "," + city);
        tv_order.setText(pricestring);


        int tax = 0;

//        int finalPrice = (Integer.valueOf(pricestring) * tax) / 100;

        double num1=Double.parseDouble(pricestring);
        double num2=Double.parseDouble(String.valueOf(tax));
        double sum= num1 + num2;
        Log.e("finalPrice", String.valueOf(sum));

//        String deliveryprice = "15";
//        String mynum1 = deliveryprice;
//        final int delivery = Integer.parseInt(mynum1);

//        String mynum2 = pricestring;
//        final int backTotalPrice = Integer.parseInt(mynum2);
////        valueprice = delivery + backTotalPrice + finalPrice;
//        valueprice = (int) (backTotalPrice + sum);

        tv_totalprice.setText(String.valueOf(sum));
        Log.e("sdvcd", addressId);

    }

    private void init() {
        tool_back = findViewById(R.id.tool_back);
        txt_ChangeAddress = findViewById(R.id.txt_ChangeAddress);
        txt_ChangeCard = findViewById(R.id.txt_ChangeCard);
        tv_name = findViewById(R.id.tv_name);
        tv_address = findViewById(R.id.tv_address);
        li_orderSubmit = findViewById(R.id.li_orderSubmit);
        place_order = findViewById(R.id.place_order);
        tv_order = findViewById(R.id.tv_order);
        tv_totalprice = findViewById(R.id.tv_totalprice);
        tv_deliverycharges = findViewById(R.id.tv_deliverycharges);

    }

    private void listiners() {
        tool_back.setOnClickListener(this);
        txt_ChangeAddress.setOnClickListener(this);
        txt_ChangeCard.setOnClickListener(this);
        li_orderSubmit.setOnClickListener(this);
        place_order.setOnClickListener(this);
    }

    private void setLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = activity.getWindow().getDecorView().getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onClick(View v) {

        if (v == tool_back) {
            finish();
        } else if (v == txt_ChangeAddress) {
            Intent intent = new Intent(this, ShippingAddressActivity.class);
            startActivity(intent);
        } else if (v == txt_ChangeCard) {
            Intent intent = new Intent(this, SelectCardActivity.class);
            startActivity(intent);
        } else if (v == li_orderSubmit) {
            Toast.makeText(activity, "Submit your Order", Toast.LENGTH_SHORT).show();
        } else if (v == place_order) {

//            Intent intent = new Intent(activity, AddNewCardActivity.class);
//            intent.putExtra("cartlistData", cartlistData);
//            intent.putExtra("addressId", String.valueOf(addressId));
//            intent.putExtra("amount", String.valueOf(tv_totalprice));
//            startActivity(intent);

//            Intent intent = new Intent(activity, AddNewCardActivity.class);
//            intent.putExtra("cartlistData", cartlistData);
//            startActivity(intent);
//
//            String data = new Gson().toJson(cartlistData);
//            Intent intent = new Intent(activity, AddNewCardActivity.class);
//            intent.putExtra("cartlistData", data);
//            intent.putExtra("price", tv_totalprice.getText().toString());
//            activity.startActivity(intent);
//
//
//            Log.d(TAG, "onid" + data);


            presenter.getcheckout(cartlistData, tv_totalprice, addressId);
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
    protected void onResume() {
        name = CSPreferences.readString(activity, Utils.GETFULLADDRESS);
        address = CSPreferences.readString(activity, Utils.GETADDRESS);
        city = CSPreferences.readString(activity, Utils.GETCITY);
        tv_name.setText(name);
        tv_address.setText(address);
        tv_address.setText(address + "," + city);

        super.onResume();
    }
}