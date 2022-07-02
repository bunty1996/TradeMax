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
import com.trademax.Models.Favourite.FavouriteDatum;
import com.trademax.R;

import java.util.ArrayList;

public class FavouriteGridAdapter extends BaseAdapter {

    private final ArrayList<FavouriteDatum> favouriteDatumList;
    private Activity activity;
    private OnViewClick viewClick;

    public FavouriteGridAdapter(Activity activity, ArrayList<FavouriteDatum> favouriteDatumList) {

        this.activity = activity;
        this.favouriteDatumList = favouriteDatumList;
    }

    @Override
    public Object getItem(int position) {
        return favouriteDatumList.get(position);
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
            listitemView = LayoutInflater.from(activity).inflate(R.layout.item_favourite_gridlayout, parent, false);
        }


//        LinearLayout linear_item = listitemView.findViewById(R.id.linear_item);
//        linear_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(activity, HomeItemsActivity.class);
////                Bundle bundle = new Bundle();
////                bundle.putParcelableArrayList("arrayListData",courseModelArrayList.get(position));
//////                intent.putExtra("myArrayList", String.valueOf(courseModelArrayList.get(position)));
////                activity.startActivity(intent);
////                viewClick.onViewClick(position);
//            }
//        });
        TextView tv_firstPrice = listitemView.findViewById(R.id.tv_firstPrice);
        TextView tv_name = listitemView.findViewById(R.id.tv_name);
        TextView tv_paintName = listitemView.findViewById(R.id.tv_paintName);
        ImageView img_product = listitemView.findViewById(R.id.img_product);
        ImageView btn_close = listitemView.findViewById(R.id.btn_close);
        LinearLayout linear_item = listitemView.findViewById(R.id.linear_item);

        linear_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewClick.onitemclick(position);
            }
        });
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewClick.onitemremove(position);

            }
        });

        if (!(favouriteDatumList.get(position).getProduct() == null)) {

            tv_firstPrice.setText(favouriteDatumList.get(position).getProduct().getVariation().getItems().get(0).getPrice() + "$");

            tv_name.setText(favouriteDatumList.get(position).getProduct().getName());
            tv_paintName.setText(favouriteDatumList.get(position).getProduct().getDescription());
            Glide.with(activity).load(favouriteDatumList.get(position).getProduct().getProductFiles().get(0).getUrl()).placeholder(R.drawable.logo).into(img_product);
        }

        return listitemView;
    }

    @Override
    public int getCount() {
        return favouriteDatumList.size();
    }

    public void ViewClickListner(OnViewClick click) {
        this.viewClick = click;

    }

    public interface OnViewClick {
        void onitemclick(int position);

        void onitemremove(int position);

    }


}


