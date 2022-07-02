package com.trademax.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trademax.Models.SimilarProduct.SimilarProductItem;
import com.trademax.R;

import java.util.List;

public class HomeSimilatItemsAdapter extends RecyclerView.Adapter<HomeSimilatItemsAdapter.ViewHolder> {
    private final List<SimilarProductItem> similarProductItems;
    private Activity activity;
    private OnViewClick viewClick;

    public HomeSimilatItemsAdapter(List<SimilarProductItem> similarProductItems, Activity activity) {
        this.similarProductItems = similarProductItems;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.home_similar_category_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txt_price.setText(similarProductItems.get(position).getPrice() + "$");
        holder.tv_headling.setText(similarProductItems.get(position).getName());
        holder.tv_description.setText(similarProductItems.get(position).getDescription());
        Glide.with(activity).load(similarProductItems.get(position).getProductFiles().get(0).getUrl()).into(holder.img_item);
        holder.linear_Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewClick.onlikeClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return similarProductItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_price;
        public TextView tv_headling;
        public TextView tv_description;
        public ImageView img_item;
        private LinearLayout linear_Name;

        public ViewHolder(View itemView) {
            super(itemView);
            this.img_item = (ImageView) itemView.findViewById(R.id.img_item);
            txt_price = (TextView) itemView.findViewById(R.id.txt_price);
            tv_headling = (TextView) itemView.findViewById(R.id.tv_headling);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            linear_Name = (LinearLayout) itemView.findViewById(R.id.linear_Name);
        }
    }

    public void ViewClickListner(OnViewClick click) {
        this.viewClick = click;

    }

    public interface OnViewClick {
        void onlikeClick(int position);
    }

}
