package com.trademax.Activities.AddShippingAddress;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.trademax.Activities.AddShippingAddress.presenter.AddShippingAddressPresenter;
import com.trademax.Activities.AddShippingAddress.view.AddShippingAddressView;
import com.trademax.Models.GetAddress.GetAddressDatum;
import com.trademax.R;
import com.trademax.Utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AddShippingAddressActivity extends AppCompatActivity implements View.OnClickListener, AddShippingAddressView {

    private Activity activity;
    private ImageView tool_back;

    private EditText et_name;
    private EditText et_address;
    private EditText et_city;
    private EditText et_state;
    private EditText et_zipCode;
    private EditText et_country;
    private GetAddressDatum getAddressDatum;

    private Button btn_saveAddress;
    private AddShippingAddressPresenter addShippingAddressPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLightStatusBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shipping_address);
        activity = this;
        init();
        listiners();

        String dataString = getIntent().getStringExtra("arraylistData");

        if (dataString.equalsIgnoreCase("123")) {

        } else {
            getAddressDatum = new Gson().fromJson(dataString, new TypeToken<GetAddressDatum>() {
            }.getType());

            et_name.setText(getAddressDatum.getFullName());
            et_address.setText(getAddressDatum.getAddress());
            et_city.setText(getAddressDatum.getCity());
            et_state.setText(getAddressDatum.getState());
            et_zipCode.setText(getAddressDatum.getZipCode());
            et_country.setText(getAddressDatum.getCountry());

        }

        addShippingAddressPresenter = new AddShippingAddressPresenter(activity, this,dataString);
    }

    private void init() {

        tool_back = findViewById(R.id.tool_back);
        btn_saveAddress = findViewById(R.id.btn_saveAddress);
        et_name = findViewById(R.id.et_name);
        et_address = findViewById(R.id.et_address);
        et_city = findViewById(R.id.et_city);
        et_state = findViewById(R.id.et_state);
        et_zipCode = findViewById(R.id.et_zipCode);
        et_country = findViewById(R.id.et_country);

        et_name.setEllipsize(TextUtils.TruncateAt.END);
        et_address.setEllipsize(TextUtils.TruncateAt.END);
        et_city.setEllipsize(TextUtils.TruncateAt.END);
        et_state.setEllipsize(TextUtils.TruncateAt.END);
        et_zipCode.setEllipsize(TextUtils.TruncateAt.END);
        et_country.setEllipsize(TextUtils.TruncateAt.END);

    }

    private void listiners() {

        tool_back.setOnClickListener(this);
        btn_saveAddress.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == tool_back) {
            finish();
        } else if (v == btn_saveAddress) {
            addShippingAddressPresenter.addAddressDetails(et_name, et_address, et_city, et_state, et_zipCode, et_country, getAddressDatum);
        }
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