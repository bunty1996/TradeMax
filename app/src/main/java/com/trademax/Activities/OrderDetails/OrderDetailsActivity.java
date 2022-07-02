package com.trademax.Activities.OrderDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.trademax.Adapters.OrderDetailsAdapter;
import com.trademax.Models.GetOrders.GetOrderDatum;
import com.trademax.Models.MyOrder.MyOrderCart;
import com.trademax.Models.MyOrder.MyOrderDatum;
import com.trademax.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Activity activity;
    private RecyclerView recycler_orderdetails;
    private OrderDetailsAdapter myBagAdapter;
    private ImageView tool_back;
    private MyOrderDatum myOrderCarts;

    private TextView tv_orderno;
    private TextView tv_orderDate;
    private TextView tv_trackingid;
    private TextView tv_quantity;
    private TextView tv_status;
    private TextView tv_address;
    private TextView tv_deliveryMethod;
    private TextView tv_discount;
    private TextView tv_priceTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLightStatusBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        activity = this;
        init();
        listiners();
        String dataString = getIntent().getStringExtra("orderlistData");
        myOrderCarts = new Gson().fromJson(dataString, new TypeToken<MyOrderDatum>() {
        }.getType());

        /////////////////

        tv_orderno.setText("Order No: " + myOrderCarts.getOrderID());

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = inputFormat.parse(myOrderCarts.getCreatedOn());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = outputFormat.format(date);
        tv_orderDate.setText(formattedDate);
        String size = String.valueOf(myOrderCarts.getCart().size());
        tv_quantity.setText(size);
        tv_status.setText(myOrderCarts.getStatus());

        tv_address.setText(myOrderCarts.getAddress().getAddress());
        tv_deliveryMethod.setText("");
        tv_priceTotal.setText(myOrderCarts.getTotalAmount() + "$");

        /////////////

        myBagAdapter = new OrderDetailsAdapter(myOrderCarts.getCart(), activity);
        recycler_orderdetails.setLayoutManager(new LinearLayoutManager(activity));
        recycler_orderdetails.setAdapter(myBagAdapter);
        recycler_orderdetails.setItemAnimator(new DefaultItemAnimator());

    }

    private void init() {
        recycler_orderdetails = (RecyclerView) findViewById(R.id.recycler_orderdetails);
        tool_back = (ImageView) findViewById(R.id.tool_back);

        ///////
        tv_orderno = (TextView) findViewById(R.id.tv_orderno);
        tv_orderDate = (TextView) findViewById(R.id.tv_orderDate);
        tv_trackingid = (TextView) findViewById(R.id.tv_trackingid);
        tv_quantity = (TextView) findViewById(R.id.tv_quantity);
        tv_status = (TextView) findViewById(R.id.tv_status);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_deliveryMethod = (TextView) findViewById(R.id.tv_deliveryMethod);
        tv_discount = (TextView) findViewById(R.id.tv_discount);
        tv_priceTotal = (TextView) findViewById(R.id.tv_priceTotal);


    }

    private void listiners() {
        tool_back.setOnClickListener(this);

    }

    private void setLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = activity.getWindow().getDecorView().getSystemUiVisibility(); // get current flag
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;   // add LIGHT_STATUS_BAR to flag
            activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(Color.WHITE); // optional
        }
    }

    @Override
    public void onClick(View v) {
        if (v == tool_back) {
            finish();
        }
    }
}