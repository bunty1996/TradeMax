package com.trademax.Activities.ShippingAddress;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;

import com.trademax.Activities.AddShippingAddress.AddShippingAddressActivity;
import com.trademax.Activities.ShippingAddress.presenter.ShippingAddressPresenter;
import com.trademax.Activities.ShippingAddress.view.ShippingAddressView;
import com.trademax.R;
import com.trademax.Utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ShippingAddressActivity extends AppCompatActivity implements View.OnClickListener, ShippingAddressView {

    private Activity activity;
    private RecyclerView recycler_addAddress;
    private FloatingActionButton fab_add;
    private ImageView tool_back;
    private ShippingAddressPresenter shippingAddressPresenter;
    private boolean isMyValueChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLightStatusBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_address);
        activity = this;
        init();
        listiners();

//        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
//        isMyValueChecked = sharedPref.getBoolean("checkbox", isMyValueChecked);

        shippingAddressPresenter = new ShippingAddressPresenter(activity, this);

    }

    private void init() {
        recycler_addAddress = findViewById(R.id.recycler_addAddress);
        fab_add = findViewById(R.id.fab_add);
        tool_back = findViewById(R.id.tool_back);

    }

    private void listiners() {

        fab_add.setOnClickListener(this);
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

        if (v == fab_add) {

            String data = ("123");
            Intent intent = new Intent(activity, AddShippingAddressActivity.class);
            intent.putExtra("arraylistData", data);
            activity.startActivity(intent);

        } else if (v == tool_back) {
            finish();
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
        super.onResume();
        //    isMyValueChecked = true;
        shippingAddressPresenter.getShippingAddress(recycler_addAddress);
    }

    @Override
    protected void onStart() {
        // isMyValueChecked = true;
        super.onStart();
    }
}