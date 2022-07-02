package com.trademax.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.trademax.R;

public class Painter_PlasterCategoryAdapter extends RecyclerView.Adapter<Painter_PlasterCategoryAdapter.ViewHolder>{
    private String listdata;
    private Activity activity;

    // RecyclerView recyclerView;
    public Painter_PlasterCategoryAdapter(String listdata, Activity activity) {
        this.listdata = listdata;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.painter_plaster_category_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (position == 0) {
            holder.txt_CategoryName.setText("Brick & Blocks");
        } else if (position == 1) {
            holder.txt_CategoryName.setText("Tiels");
        } else if (position == 2) {
            holder.txt_CategoryName.setText("PPC Cement");
        } else if (position == 3) {
            holder.txt_CategoryName.setText("Brick & Blocks");
        } else if (position == 4) {
            holder.txt_CategoryName.setText("Tiels");
        } else if (position == 5) {
            holder.txt_CategoryName.setText("PPC Cement");
        }
//        final MyListData myListData = listdata[position];
//        holder.textView.setText(listdata[position].getDescription());
//        holder.imageView.setImageResource(listdata[position].getImgId());
//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"click on item: "+myListData.getDescription(),Toast.LENGTH_LONG).show();
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return 6;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_CategoryName;
        public LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.txt_CategoryName = (TextView) itemView.findViewById(R.id.txt_CategoryName);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_layout);
        }
    }
}
