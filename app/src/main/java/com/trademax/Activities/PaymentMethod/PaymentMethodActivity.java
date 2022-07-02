package com.trademax.Activities.PaymentMethod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.trademax.Adapters.PaymentMethodAdapter;
import com.trademax.R;

public class PaymentMethodActivity extends AppCompatActivity implements View.OnClickListener {

    private Activity activity;
    private RecyclerView recycler_card;
    private FloatingActionButton fab_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLightStatusBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        activity = this;
        init();
        listiners();

        PaymentMethodAdapter paymentMethodAdapter = new PaymentMethodAdapter("", activity);
        recycler_card.setHasFixedSize(true);
        recycler_card.setLayoutManager(new LinearLayoutManager(activity));
        recycler_card.setAdapter(paymentMethodAdapter);
    }

    private void init() {
        recycler_card = findViewById(R.id.recycler_card);
        fab_add = findViewById(R.id.fab_add);
    }

    private void listiners() {
        fab_add.setOnClickListener(this);
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
    
        }
    }
}