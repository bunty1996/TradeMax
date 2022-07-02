package com.trademax.Activities.Filter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.trademax.Activities.Filter.presenter.FilterPresenter;
import com.trademax.Activities.Filter.view.Filterview;
import com.trademax.R;
import com.trademax.Utils.Utils;

import it.mirko.rangeseekbar.OnRangeSeekBarListener;
import it.mirko.rangeseekbar.RangeSeekBar;


public class FilterActivity extends AppCompatActivity implements View.OnClickListener, Filterview {
    String[] color = {"Red", "Green", "Blue", "White","Purple","Pink"};
    String[] size = {"1", "2", "3", "4","5","6"};

    private Activity activity;
    private ImageView tool_back;
    private it.mirko.rangeseekbar.RangeSeekBar rangeSeekBar;
    private TextView tv_priceleft;
    private TextView tv_priceright;
    private Button btnapply;
    private FilterPresenter filterPresenter;
    private Button btnclear;
    private String max;
    private String min;
    private String sizes;
    private String colors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLightStatusBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        activity = this;

        init();
        listeners();
        filterPresenter = new FilterPresenter(activity, this);

        Spinner spincolor = (Spinner) findViewById(R.id.spinner);
        Spinner spinsize = (Spinner) findViewById(R.id.spinner_filtersize);
        spincolor.getBackground().setColorFilter(getResources().getColor(R.color.appcolor), PorterDuff.Mode.SRC_ATOP);
        spinsize.getBackground().setColorFilter(getResources().getColor(R.color.appcolor), PorterDuff.Mode.SRC_ATOP);

        spincolor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                colors = spincolor.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.spinner,color);
        arrayAdapter.setDropDownViewResource(R.layout.spinner);
        spincolor.setAdapter(arrayAdapter);

        //brand
        spinsize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sizes = spinsize.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter arrayAdaptersize = new ArrayAdapter(this,R.layout.spinner,size);
        arrayAdaptersize.setDropDownViewResource(R.layout.spinner);
        spinsize.setAdapter(arrayAdaptersize);

       rangeSeekBar.setRangeColor(Color.parseColor("#91AE41"));

        rangeSeekBar.setTrackColor(Color.parseColor("#828282"));
        rangeSeekBar.setStartProgress(0);
        rangeSeekBar.setEndProgress(100);
        rangeSeekBar.setMinDifference(15);

        rangeSeekBar.setOnRangeSeekBarListener(new OnRangeSeekBarListener() {
            @Override
            public void onRangeValues(RangeSeekBar rangeSeekBar, int start, int end) {
                tv_priceleft.setText(""+start);
                tv_priceright.setText(""+end);
                min = String.valueOf(start);
                max = String.valueOf(end);
            }
        });
    }

    private void init() {
        tool_back = findViewById(R.id.tool_back);
        rangeSeekBar =  findViewById(R.id.rangeSeekBar);
        tv_priceleft = findViewById(R.id.tv_priceleft);
        tv_priceright = findViewById(R.id.tv_priceright);
        btnapply = findViewById(R.id.btn_apply);
        btnclear = findViewById(R.id.btn_clear);

    }

    private void listeners() {
        tool_back.setOnClickListener(this);
        tv_priceleft.setOnClickListener(this);
        tv_priceright.setOnClickListener(this);
        btnapply.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == tool_back) {
            finish();
        } else if (v == btnapply) {
            filterPresenter.getfilterproduct(sizes,min,max,colors);
        } else if (v ==btnclear){
            Toast.makeText(activity, "Product cleared Sucessfully", Toast.LENGTH_SHORT).show();
        }
    }

    private void setLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            int flags = activity.getWindow().getDecorView().getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(Color.WHITE);
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