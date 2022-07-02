package com.trademax.Activities.Builder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trademax.Activities.Builder.presenter.BuilderPresenter;
import com.trademax.Activities.Builder.view.BuilderView;
import com.trademax.Adapters.BuilderCategoryAdapter;
import com.trademax.Models.Favourite.FavouriteProduct;
import com.trademax.Models.GetAddress.GetAddressDatum;
import com.trademax.Models.GetCategoriesDetails.GetCategoriesDetailsDatum;
import com.trademax.Models.HomeGetAll.GetAllProductsItem;
import com.trademax.Models.MyOrder.MyOrderCart;
import com.trademax.R;
import com.trademax.Utils.Utils;

import java.util.List;

public class BuilderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, BuilderView {
    private static final String TAG = "BuilderActivity";
    String[] courses = {"LOW-TO-HIGH", "HIGH-TO-LOW", "NEWEST", "POPULAR"};

    private Activity activity;
    private ImageView tool_back;
    private GridView grid_builder;
    private BuilderPresenter builderPresenter;
    private RecyclerView recyclerView;
    private String dataString;
    private String subcatId;
    private GetCategoriesDetailsDatum list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLightStatusBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_builder);
        activity = this;

        init();
        listeners();

        dataString = getIntent().getStringExtra("arraylistData");
        list = new Gson().fromJson(dataString, new TypeToken<GetCategoriesDetailsDatum>() {
        }.getType());

        subcatId = list.getId();

        builderPresenter = new BuilderPresenter(activity, this, grid_builder, subcatId);

        Spinner spin = (Spinner) findViewById(R.id.spinner_filterlist);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter arrayAdapter
                = new ArrayAdapter(
                this,
                R.layout.spinner_item,
                courses);

        arrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spin.setAdapter(arrayAdapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("LOW-TO-HIGH")) {
                    builderPresenter.getSortproductBySubCategoriesMethod(1, 100, subcatId, "low");
                } else if (selectedItem.equals("HIGH-TO-LOW")) {
                    builderPresenter.getSortproductBySubCategoriesMethod(1, 100, subcatId, "high");

                } else if (selectedItem.equals("NEWEST")) {
                    builderPresenter.getSortproductBySubCategoriesMethod(1, 100, subcatId, "");

                } else if (selectedItem.equals("POPULAR")) {
                    builderPresenter.getSortproductBySubCategoriesMethod(1, 100, subcatId, "");

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        builderPresenter.getproductBySubCategoriesMethod(subcatId);


//        BuilderCategoryAdapter builderCategoryAdapter = new BuilderCategoryAdapter("", activity);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
//        recyclerView.setAdapter(builderCategoryAdapter);
//
//        builderPresenter.getproductBySubCategoriesMethod(subcatId);

    }

    private void listeners() {
        tool_back.setOnClickListener(this);

    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_categories);

        grid_builder = findViewById(R.id.grid_builder);
        tool_back = findViewById(R.id.tool_back);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        if (v == tool_back) {
            finish();
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