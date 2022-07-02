package com.trademax.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.trademax.Models.HomeGetAll.GetAllProductsItem;
import com.trademax.R;

import java.util.ArrayList;

public class HomeGridAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<GetAllProductsItem> courseModelArrayList;
    private OnViewClick viewClick;

    public HomeGridAdapter(Activity activity, ArrayList<GetAllProductsItem> items) {
        this.activity = activity;
        this.courseModelArrayList = items;
    }

    @Override
    public Object getItem(int position) {
        return courseModelArrayList.get(position);
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
            listitemView = LayoutInflater.from(activity).inflate(R.layout.home_gridlayout, parent, false);
        }

        TextView firstLine = listitemView.findViewById(R.id.firstLine);
        LinearLayout linear_item = listitemView.findViewById(R.id.linear_item);
        linear_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(activity, HomeItemsActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelableArrayList("arrayListData",courseModelArrayList.get(position));
////                intent.putExtra("myArrayList", String.valueOf(courseModelArrayList.get(position)));
//                activity.startActivity(intent);
                viewClick.onViewClick(position);
            }
        });
        ImageView img_item = listitemView.findViewById(R.id.img_item);
        TextView secondLine = listitemView.findViewById(R.id.secondLine);
        firstLine.setText(courseModelArrayList.get(position).getName());
        secondLine.setText(courseModelArrayList.get(position).getSubCategory().getName());
//        secondLine.setText(courseModel.getSecondname());
//        image.setImageResource(courseModel.getImage());
        Glide.with(activity).load(courseModelArrayList.get(position).getProductFiles().get(0).getUrl()).into(img_item);
        return listitemView;
    }

    @Override
    public int getCount() {
        return courseModelArrayList.size();
    }

    public void ViewClickListner(OnViewClick click) {
        this.viewClick = click;
    }


    public interface OnViewClick {
        void onViewClick(int position);


    }

}