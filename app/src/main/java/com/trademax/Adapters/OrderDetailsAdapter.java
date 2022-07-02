package com.trademax.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trademax.Models.GetOrders.GetOrderDatum;
import com.trademax.Models.MyOrder.MyOrderCart;
import com.trademax.Models.MyOrder.MyOrderDatum;
import com.trademax.R;

import java.util.List;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder> {
    private final List<MyOrderCart> myOrderData;
    private Activity activity;

    public OrderDetailsAdapter(List<MyOrderCart> myOrderData, Activity activity) {
        this.myOrderData = myOrderData;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.orderdetails_item, parent, false);
        OrderDetailsAdapter.ViewHolder viewHolder = new OrderDetailsAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OrderDetailsAdapter.ViewHolder holder, int position) {

//        Glide.with(activity).load(myOrderData.get(position).getCartId().getProduct().getProductFiles().get(0).getUrl()).into(holder.img_item);
        holder.tv_productName.setText(myOrderData.get(position).getCartId().getProduct().getName());
        holder.tv_productTitle.setText(myOrderData.get(position).getCartId().getProduct().getContent());

//        if (myOrderData.get(position).getCartId().getProduct().getVariation().getKey().equalsIgnoreCase("Size")) {
//            holder.tv_color.setText("Size : " + myOrderData.get(position).getCartId().getProduct().getVariation().getItems().get(position).getValue());
//        } else {
//            holder.tv_color.setText("Color :  " + myOrderData.get(position).getCartId().getProduct().getVariation().getItems().get(position).getValue());
//        }
        holder.tv_quantity.setText("Quantity : " + myOrderData.get(position).getCartId().getQuantity());

        String a = null;
        String b = null ;

        a = String.valueOf(myOrderData.get(position).getCartId().getQuantity());
        b = String.valueOf(myOrderData.get(position).getCartId().getProduct().getPrice());

        double num1 = Double.parseDouble(a);
        double num2 = Double.parseDouble(b);
        double sum = num1 * num2;


//        holder.tv_price.setText(myOrderData.get(position).getCartId().getProduct().getVariation().getItems().get(position).getPrice() + "$");
        holder.tv_price.setText(sum + "$");

        Glide.with(activity).load(myOrderData.get(position).getCartId().getProduct().getProductFiles().get(0).getUrl()).into(holder.img_item);

//        if (position == 0){
//            holder.img_item.setImageResource(R.drawable.paint12);
//        }else if (position == 1){
//            holder.img_item.setImageResource(R.drawable.bottle);
//        }else if (position == 2){
//            holder.img_item.setImageResource(R.drawable.paint13);
//        }


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
        return myOrderData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_productName;
        public TextView tv_productTitle;
        //        public TextView tv_color;
        public TextView tv_quantity;
        public TextView tv_price;
        public ImageView img_item;

        public ViewHolder(View itemView) {
            super(itemView);
            this.img_item = (ImageView) itemView.findViewById(R.id.img_item);
            this.tv_productName = (TextView) itemView.findViewById(R.id.tv_productName);
            this.tv_productTitle = (TextView) itemView.findViewById(R.id.tv_productTitle);
//            this.tv_color = (TextView) itemView.findViewById(R.id.tv_color);
            this.tv_quantity = (TextView) itemView.findViewById(R.id.tv_quantity);
            this.tv_price = (TextView) itemView.findViewById(R.id.tv_price);
        }
    }
}