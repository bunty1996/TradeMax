package com.trademax.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.trademax.Activities.MainActivity;
import com.trademax.Models.ProductBySubCategories.ProductBySubCatItem;
import com.trademax.R;
import com.trademax.Utils.Utils;

import java.util.ArrayList;

public class BuilderGridAdapter extends BaseAdapter {

    private final ArrayList<ProductBySubCatItem> productBySubCatItemList;
    private Activity activity;
    private OnViewClick viewClick;
    private String loginValue = "";
    private SharedPreferences pref;


    public BuilderGridAdapter(Activity activity, ArrayList<ProductBySubCatItem> productBySubCatItemList) {

        this.activity = activity;
        this.productBySubCatItemList = productBySubCatItemList;
    }


    @Override
    public int getCount() {
        return productBySubCatItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return productBySubCatItemList.get(position);
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
            listitemView = LayoutInflater.from(activity).inflate(R.layout.item_painter_plaster_gridlayout, parent, false);
        }

        pref = activity.getSharedPreferences("pref", Context.MODE_PRIVATE);
        loginValue = pref.getString(Utils.USERLOGIN, "");


        TextView tv_price = listitemView.findViewById(R.id.tv_price);
        TextView tv_name = listitemView.findViewById(R.id.tv_name);
        TextView tv_discription = listitemView.findViewById(R.id.tv_discription);
        ImageView img_item = listitemView.findViewById(R.id.img_item);
        ImageView img_like_dislike = listitemView.findViewById(R.id.img_like_dislike);

        tv_name.setText(productBySubCatItemList.get(position).getName());
        tv_price.setText(productBySubCatItemList.get(position).getPrice() + "$");
        tv_discription.setText(productBySubCatItemList.get(position).getSubCategory().getName());
        Glide.with(activity).load(productBySubCatItemList.get(position).getProductFiles().get(0).getUrl()).into(img_item);

        if (productBySubCatItemList.get(position).getIsLiked().equals(true)) {
            img_like_dislike.setImageResource(R.drawable.fill_heart);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                img_like_dislike.setColorFilter(activity.getResources().getColor(R.color.appcolor));

            }
        } else {
            img_like_dislike.setImageResource(R.drawable.null_heart);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                img_like_dislike.setColorFilter(activity.getResources().getColor(R.color.appcolor));

            }
        }
        img_like_dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginValue.equalsIgnoreCase("true")) {
                    viewClick.onlikeClick(position, img_like_dislike, productBySubCatItemList.get(position).getId());
                } else {
                    Intent intent = new Intent(activity, MainActivity.class);
                    activity.startActivity(intent);
                }
            }
        });

        return listitemView;
    }

    public void ViewClickListner(OnViewClick click) {
        this.viewClick = click;

    }


    public interface OnViewClick {
        void onlikeClick(int position, ImageView imglike, String productId);
    }

}
