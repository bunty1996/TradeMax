package com.trademax.Activities.Painters_Plasters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trademax.Activities.Filter.FilterActivity;
import com.trademax.Activities.Painters_Plasters.presenter.Painter_PlastersPresenter;
import com.trademax.Activities.Painters_Plasters.view.Painter_PlastersView;
import com.trademax.Adapters.Painter_PlasterCategoryAdapter;
import com.trademax.Models.GetCategoriesDetails.GetCategoriesDetailsDatum;
import com.trademax.R;
import com.trademax.Utils.Utils;

public class Painter_PlastersActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, Painter_PlastersView {

    String[] courses = {"LOW-TO-HIGH", "HIGH-TO-LOW", "NEWEST", "POPULAR"};

    private Activity activity;
    private ImageView tool_back;
    private TextView txt_filter;
    private GridView grid_painter_plaster;
    private Painter_PlastersPresenter painter_plastersPresenter;
    private RecyclerView recyclerView;
    private EditText search_view;
    private ImageView imgsearch;
    private ImageView tool_search;
    private SwipeRefreshLayout refreshlayout;
    private LinearLayout ll_search;
    private String dataString;
    private String subcatId;
    private GetCategoriesDetailsDatum list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLightStatusBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painter__plasters);
        activity = this;
        init();
        listeners();

        dataString = getIntent().getStringExtra("arraylistData");
        list = new Gson().fromJson(dataString, new TypeToken<GetCategoriesDetailsDatum>() {
        }.getType());

        subcatId = list.getId();

        painter_plastersPresenter = new Painter_PlastersPresenter(activity, this, grid_painter_plaster, subcatId);

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
                    painter_plastersPresenter.getSortproductBySubCategoriesMethod(1, 10, subcatId, "low");
                } else if (selectedItem.equals("HIGH-TO-LOW")) {
                    painter_plastersPresenter.getSortproductBySubCategoriesMethod(1, 10, subcatId, "high");
                } else if (selectedItem.equals("NEWEST")) {
                    painter_plastersPresenter.getSortproductBySubCategoriesMethod(1, 10, subcatId, "");
                } else if (selectedItem.equals("POPULAR")) {
                    painter_plastersPresenter.getSortproductBySubCategoriesMethod(1, 10, subcatId, "");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        painter_plastersPresenter.getproductBySubCategoriesMethod(subcatId);

        Painter_PlasterCategoryAdapter painter_plasterCategoryAdapter = new Painter_PlasterCategoryAdapter("", activity);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(painter_plasterCategoryAdapter);

        search_view.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (search_view.getText().toString().trim()
                        .length() == 0 || search_view.getText().toString().trim()
                        .equals("")) {

                } else {
                    painter_plastersPresenter.getSearchMethod(search_view.getText().toString());
                    ll_search.setVisibility(View.GONE);

                }
                return false;
            }
        });
        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (search_view.getText().toString().trim()
                        .length() == 0 || search_view.getText().toString().trim()
                        .equals("")
                ) {
                } else {
                    painter_plastersPresenter.getSearchMethod(search_view.getText().toString());
                    ll_search.setVisibility(View.GONE);

                }
            }
        });

        search_view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if (search_view.getText().toString().trim()
                        .length() == 0 || search_view.getText().toString().trim()
                        .isEmpty() || search_view.getText().toString().trim().equals("")
                ) {

                } else {

                }
            }
        });

        refreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                painter_plastersPresenter.getproductBySubCategoriesMethod(subcatId);
                refreshlayout.setRefreshing(false);
            }
        });
    }

    private void listeners() {
        tool_back.setOnClickListener(this);
        txt_filter.setOnClickListener(this);
        tool_search.setOnClickListener(this);
    }

    private void init() {
        grid_painter_plaster = findViewById(R.id.grid_painter_plaster);
        tool_back = findViewById(R.id.tool_back);
        txt_filter = findViewById(R.id.txt_filter);
        search_view = findViewById(R.id.search_view);
        tool_search = findViewById(R.id.tool_search);
        imgsearch = findViewById(R.id.imgsearch);
        ll_search = findViewById(R.id.ll_search);
        refreshlayout = findViewById(R.id.refreshlayout);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_categories);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    ;

    @Override
    public void onClick(View v) {

        if (v == tool_back) {
            finish();
        } else if (v == txt_filter) {
            Intent intent = new Intent(activity, FilterActivity.class);
            startActivity(intent);
        } else if (v == tool_search) {
            ll_search.setVisibility(View.VISIBLE);
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