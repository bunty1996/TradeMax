package com.trademax.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.trademax.Models.HomeGetAll.GetAllProductsItem;
import com.trademax.R;

import java.util.ArrayList;

public class HomeActivityGridAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<GetAllProductsItem> getAllProductsItems;

    public HomeActivityGridAdapter(Activity activity, ArrayList<GetAllProductsItem> itemdetails) {

        this.activity = activity;
        this.getAllProductsItems = itemdetails;
    }

    @Override
    public int getCount() {
        return getAllProductsItems.size();
    }

    @Override
    public Object getItem(int position) {
        return getAllProductsItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(activity).inflate(R.layout.home_gridactivity_items, parent, false);
        }

        TextView firstLine = listitemView.findViewById(R.id.firstLine);

        ImageView image = listitemView.findViewById(R.id.image);
        TextView secondLine = listitemView.findViewById(R.id.secondLine);
        firstLine.setText(getAllProductsItems.get(position).getName());
//        secondLine.setText(courseModel.getSecondname());
//        image.setImageResource(courseModel.getImage());
        return listitemView;
    }
}