package com.trademax.Activities.FavouriteItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.trademax.Activities.FavouriteItem.presenter.Favitempresenter;
import com.trademax.Activities.FavouriteItem.view.Favitemview;
import com.trademax.Activities.MainActivity;
import com.trademax.Adapters.FavouriteSliderAdapter;
import com.trademax.Models.Favourite.FavouriteProduct;
import com.trademax.R;
import com.trademax.Utils.Utils;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class FavouriteitemActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, Favitemview {

    String[] Quantity = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    private Favitempresenter favouritePresenter;
    private Activity activity;
    private ImageView img_back;
    private TextView txt_name;
    private TextView txt_price;
    private TextView txt_content;
    private ViewPager viewPager;
    private TabLayout indicator;
    private Button btn_addToCart;
    private RecyclerView recyclerview;
    private ImageView img_like;
    private String dataString;
    private FavouriteProduct list;
    private String product_price;
    private String productId;
    private String valueid;
    private String value;
    private String categori_id;
    private String loginValue = "";

    private SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLightStatusBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favouriteitem);
        activity = this;
        init();
        listiners();

        dataString = getIntent().getStringExtra("arraylistData");
        list = new Gson().fromJson(dataString, new TypeToken<FavouriteProduct>() {
        }.getType());

        productId = list.getId();
        product_price = list.getPrice();
        categori_id = list.getSubCategory().getId();

        pref = activity.getSharedPreferences("pref", Context.MODE_PRIVATE);
        loginValue = pref.getString(Utils.USERLOGIN, "");

        if (list.getIsLiked().equals(true)) {
            img_like.setImageResource(R.drawable.fill_heart);
            img_like.setColorFilter(activity.getResources().getColor(R.color.appcolor));
        } else if (list.getIsLiked().equals(false)) {
            img_like.setImageResource(R.drawable.null_heart);
            img_like.setColorFilter(activity.getResources().getColor(R.color.appcolor));
        }

        favouritePresenter = new Favitempresenter(this, activity, recyclerview);
        favouritePresenter.getSimilarProduct(categori_id, productId);

        Spinner spin_key = (Spinner) findViewById(R.id.spin_color);
        spin_key.setOnItemSelectedListener(this);

        Spinner spin_quantity = (Spinner) findViewById(R.id.spin_quantity);
        spin_quantity.setOnItemSelectedListener(this);

        String variationKey = list.getVariation().getKey();

        if (variationKey.equals("Size") || variationKey.equals("size")) {
            ArrayList itemlist = new ArrayList();
            for (int i = 0; i < list.getVariation().getItems().size(); i++) {
                itemlist.add(list.getVariation().getItems().get(i).getValue());
            }

            ArrayAdapter arrayAdapter1 = new ArrayAdapter(activity, R.layout.spinner_item, itemlist);
            arrayAdapter1.setDropDownViewResource(R.layout.spinner_homeitem);
            spin_key.setAdapter(arrayAdapter1);

        } else if (variationKey.equals("color") || variationKey.equals("Color")) {
            ArrayList itemlist = new ArrayList();
            for (int i = 0; i < list.getVariation().getItems().size(); i++) {
                itemlist.add(list.getVariation().getItems().get(i).getValue());
            }

            ArrayAdapter arrayAdapter1 = new ArrayAdapter(activity, R.layout.spinner_item, itemlist);
            arrayAdapter1.setDropDownViewResource(R.layout.spinner_homeitem);
            spin_key.setAdapter(arrayAdapter1);
        }

        /*if (variationKey.equals("Size")) {
            ArrayList itemlist = new ArrayList();
            for (int i = 0; i < list.getVariation().getItems().size(); i++) {
                itemlist.add(list.getVariation().getItems().get(i).getValue());
            }


            ArrayAdapter arrayAdapter1 = new ArrayAdapter(activity, R.layout.spinner_item, itemlist);
            arrayAdapter1.setDropDownViewResource(R.layout.spinner_homeitem);
            spin_key.setAdapter(arrayAdapter1);

        } else if (variationKey.equals("color")) {

        }*/

        spin_key.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //     for (int i = 0; i < list.getVariation().getItems().size(); i++) {
                valueid = list.getVariation().getItems().get(position).getId();
                // }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spin_quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                value = spin_quantity.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter arrayAdapter2
                = new ArrayAdapter(
                this,
                R.layout.spinner_item,
                Quantity);

        arrayAdapter2.setDropDownViewResource(R.layout.spinner_item);
        spin_quantity.setAdapter(arrayAdapter2);

        viewPager.setAdapter(new FavouriteSliderAdapter(this, list.getProductFiles()));
        indicator.setupWithViewPager(viewPager, true);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 2000, 4000);
    }

    @Override
    public void onClick(View v) {

        if (v == btn_addToCart) {
            favouritePresenter.getAddtoCart(productId, product_price, valueid, value);
        } else if (v == img_back) {
            finish();
        } else if (v == img_like) {
            if (loginValue.equalsIgnoreCase("true")) {
                favouritePresenter.HomeLikeMeathod(productId);
            } else {
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
            }
        }
    }

    private void init() {
//        idGVcourses = findViewById(R.id.idGVcourses);
        img_back = findViewById(R.id.img_back);
        txt_name = findViewById(R.id.txt_name);
        txt_price = findViewById(R.id.txt_price);
        btn_addToCart = findViewById(R.id.btn_addToCart);
        txt_content = findViewById(R.id.txt_content);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        indicator = (TabLayout) findViewById(R.id.indicator);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        img_like = (ImageView) findViewById(R.id.img_like);

    }

    private void listiners() {
        img_back.setOnClickListener(this);
        btn_addToCart.setOnClickListener(this);
        img_like.setOnClickListener(this);

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
    public void changeFavouriteImage(String message) {

        if (message.equalsIgnoreCase("Product Liked Successfully!!")) {
            img_like.setImageResource(R.drawable.fill_heart);
            img_like.setColorFilter(activity.getResources().getColor(R.color.appcolor));
        } else {
            img_like.setImageResource(R.drawable.null_heart);
            img_like.setColorFilter(activity.getResources().getColor(R.color.appcolor));
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < list.getProductFiles().size() - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
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


}