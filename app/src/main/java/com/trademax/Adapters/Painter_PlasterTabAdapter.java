package com.trademax.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trademax.Models.GetCategoriesDetails.GetCategoriesDetailsDatum;
import com.trademax.R;
import com.trademax.Utils.WebServices;

import java.util.List;

public class Painter_PlasterTabAdapter extends RecyclerView.Adapter<Painter_PlasterTabAdapter.ViewHolder> {
    private final List<GetCategoriesDetailsDatum> searchCategoriesArrayList;
    private Activity activity;
    private OnViewClick viewClick;


    public Painter_PlasterTabAdapter(List<GetCategoriesDetailsDatum> searchCategoriesArrayList, Activity activity) {
        this.searchCategoriesArrayList = searchCategoriesArrayList;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_printertab_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(searchCategoriesArrayList.get(position).getName());
//        Picasso.with(activity)
//                .load(searchCategoriesArrayList.get(position).getImage())
//                .placeholder(R.drawable.cross)
//                .into(holder.imageView);

        Glide.with(activity).load(WebServices.ImageBaseURL +searchCategoriesArrayList.get(position).getImage()).into(holder.imageView);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewClick.onid(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchCategoriesArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.textView1);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_layout);
        }
    }
    public void ViewClickListner(OnViewClick click) {
        this.viewClick = click;
    }

    public interface OnViewClick {
        void onid(int position);
    }
}
