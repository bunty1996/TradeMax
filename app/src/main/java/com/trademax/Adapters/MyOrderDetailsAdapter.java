package com.trademax.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.trademax.Activities.OrderDetails.OrderDetailsActivity;
import com.trademax.Models.MyOrder.MyOrderDatum;
import com.trademax.R;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyOrderDetailsAdapter extends RecyclerView.Adapter<MyOrderDetailsAdapter.ViewHolder> {
    private String value;
    private List<MyOrderDatum> data;
    private Activity activity;

    public MyOrderDetailsAdapter(List<MyOrderDatum> data, Activity activity, String value) {
        this.data = data;
        this.activity = activity;
        this.value = value;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.myorder_detail_item, parent, false);
        MyOrderDetailsAdapter.ViewHolder viewHolder = new MyOrderDetailsAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(MyOrderDetailsAdapter.ViewHolder holder, int position) {

        if (data.size() == 0) {
            holder.linear_item.setVisibility(View.GONE);
            holder.noOrderStatus.setVisibility(View.VISIBLE);
        } else {
            holder.linear_item.setVisibility(View.VISIBLE);
            holder.noOrderStatus.setVisibility(View.GONE);
        }

//        if (data.get(position).getStatus().equalsIgnoreCase("Ordered")) {
//
//        } else if (data.get(position).getStatus().equalsIgnoreCase("Shipping")) {
//
//        } else if (data.get(position).getStatus().equalsIgnoreCase("Delivered")) {
//
//        } else {
//
//
//        }
//
//        if (value.equalsIgnoreCase("1")) {
//            if (data.get(position).getStatus().equalsIgnoreCase("Ordered")) {
//                holder.tv_orderno.setText("Order No : " + data.get(position).getOrderID());
//                holder.tv_amount.setText("" + data.get(position).getTotalAmount() + "$");
//                holder.tv_status.setText("" + data.get(position).getStatus());
//                holder.tv_quantity.setText("" + data.get(position).getCart().size());
//
//                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//                SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
//                Date date = null;
//                try {
//                    date = inputFormat.parse(data.get(position).getCreatedOn());
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                String formattedDate = outputFormat.format(date);
//                holder.tv_date.setText(formattedDate);
//
//                holder.linear_item.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        String orderList = new Gson().toJson(data.get(position).getCart());
////                String orderList = new Gson().toJson(data.get(position).getCart().get(position).getCartId());
//                        Intent intent = new Intent(activity, OrderDetailsActivity.class);
//                        intent.putExtra("orderlistData", orderList);
//                        activity.startActivity(intent);
//                    }
//                });
//
//            }
//        } else if (value.equalsIgnoreCase("2")) {
//            if (data.get(position).getStatus().equalsIgnoreCase("Shipping")) {
//                holder.tv_orderno.setText("Order No : " + data.get(position).getOrderID());
//                holder.tv_amount.setText("" + data.get(position).getTotalAmount() + "$");
//                holder.tv_status.setText("" + data.get(position).getStatus());
//                holder.tv_quantity.setText("" + data.get(position).getCart().size());
//
//                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//                SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
//                Date date = null;
//                try {
//                    date = inputFormat.parse(data.get(position).getCreatedOn());
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                String formattedDate = outputFormat.format(date);
//                holder.tv_date.setText(formattedDate);
//
//                holder.linear_item.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        String orderList = new Gson().toJson(data.get(position).getCart());
////                String orderList = new Gson().toJson(data.get(position).getCart().get(position).getCartId());
//                        Intent intent = new Intent(activity, OrderDetailsActivity.class);
//                        intent.putExtra("orderlistData", orderList);
//                        activity.startActivity(intent);
//                    }
//                });
//            }
//        } else if (value.equalsIgnoreCase("3")) {
//            if (data.get(position).getStatus().equalsIgnoreCase("Delivered")) {
//                holder.tv_orderno.setText("Order No : " + data.get(position).getOrderID());
//                holder.tv_amount.setText("" + data.get(position).getTotalAmount() + "$");
//                holder.tv_status.setText("" + data.get(position).getStatus());
//                holder.tv_quantity.setText("" + data.get(position).getCart().size());
//
//                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//                SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
//                Date date = null;
//                try {
//                    date = inputFormat.parse(data.get(position).getCreatedOn());
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                String formattedDate = outputFormat.format(date);
//                holder.tv_date.setText(formattedDate);
//
//                holder.linear_item.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        String orderList = new Gson().toJson(data.get(position).getCart());
////                String orderList = new Gson().toJson(data.get(position).getCart().get(position).getCartId());
//                        Intent intent = new Intent(activity, OrderDetailsActivity.class);
//                        intent.putExtra("orderlistData", orderList);
//                        activity.startActivity(intent);
//                    }
//                });
//            }
//        } else {
        holder.tv_orderno.setText("Order No : " + data.get(position).getOrderID());
        holder.tv_amount.setText("" + data.get(position).getTotalAmount() + "$");
        holder.tv_status.setText("" + data.get(position).getStatus());
        holder.tv_quantity.setText("" + data.get(position).getCart().size());

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
        Date date = null;
        try {
            date = inputFormat.parse(data.get(position).getCreatedOn());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = outputFormat.format(date);
        holder.tv_date.setText(formattedDate);

        holder.linear_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orderList = new Gson().toJson(data.get(position));
//                String orderList = new Gson().toJson(data.get(position).getCart());
//                String orderList = new Gson().toJson(data.get(position).getCart().get(position).getCartId());
                Intent intent = new Intent(activity, OrderDetailsActivity.class);
                intent.putExtra("orderlistData", orderList);
                activity.startActivity(intent);
            }
        });
//        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_orderno;
        public TextView tv_trackingid;
        public TextView tv_quantity;
        public TextView tv_status;
        public TextView tv_amount;
        public TextView tv_date;
        public LinearLayout linear_item;
        public LinearLayout noOrderStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_orderno = (TextView) itemView.findViewById(R.id.tv_orderno);
            tv_trackingid = (TextView) itemView.findViewById(R.id.tv_trackingid);
            tv_quantity = (TextView) itemView.findViewById(R.id.tv_quantity);
            tv_amount = (TextView) itemView.findViewById(R.id.tv_amount);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            linear_item = (LinearLayout) itemView.findViewById(R.id.linear_item);
            noOrderStatus = (LinearLayout) itemView.findViewById(R.id.noOrderStatus);
        }
    }

}