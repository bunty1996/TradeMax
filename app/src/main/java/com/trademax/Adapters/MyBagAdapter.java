package com.trademax.Adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.trademax.Models.CartListModel.CartlistData;
import com.trademax.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyBagAdapter extends RecyclerView.Adapter<MyBagAdapter.ViewHolder> {
    private static final String TAG = "MyBagAdapter";
    private final ArrayList<CartlistData> cartlistData;
    private Activity activity;
    private OnViewClick viewClick;
    private ArrayList<Float> pricelist = new ArrayList<Float>();

    public MyBagAdapter(ArrayList<CartlistData> cartlistData, Activity activity) {
        this.cartlistData = cartlistData;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.mybag_item, parent, false);
        MyBagAdapter.ViewHolder viewHolder = new MyBagAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyBagAdapter.ViewHolder holder, int position) {

        holder.morePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(activity, v);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                Log.d(TAG, "onClick" + cartlistData.get(position).getIsLiked());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.one:
                                if (cartlistData.get(position).getIsLiked() == true) {
                                    Toast.makeText(activity, "Already Liked", Toast.LENGTH_SHORT).show();
                                } else {
                                    viewClick.onfav(position);
                                }
                                break;
                            case R.id.two:
                                viewClick.ondeleteitem(position, cartlistData.get(position).getId());

                                break;
                        }
                        return true;
                    }
                });

                popup.show();

            }
        });

        pricelist.clear();
        pricelist.add(cartlistData.get(position).getTotal());

//        viewClick.ontotalPrice(pricelist);
        viewClick.ontotalPrice(cartlistData);

        viewClick.getCartData(cartlistData);

        holder.tv_itemName.setText(cartlistData.get(position).getProduct().getName());
        holder.tv_content.setText(cartlistData.get(position).getProduct().getContent());
        if (cartlistData.get(position).getProduct().getVariation().equals("size")) {
            holder.tv_size_color.setText("Size" + cartlistData.get(position).getProduct().getVariation().getItems().get(position).getValue());
        } else {
            holder.tv_size_color.setText("Color" + cartlistData.get(position).getProduct().getVariation().getKey());
        }
        holder.tv_quantity.setText("Quantity" + " : " + cartlistData.get(position).getQuantity());
        holder.tv_price.setText("$" + " " + cartlistData.get(position).getTotal());

        Picasso.with(activity)
                .load(cartlistData.get(position).getProduct().getProductFiles().get(0).getUrl())
                .placeholder(R.drawable.cross)
                .into(holder.img_item);
    }

    @Override
    public int getItemCount() {
        return cartlistData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_itemName;
        public TextView tv_content;
        public TextView tv_size_color;
        public TextView tv_quantity;
        public TextView tv_price;
        public ImageView morePopup;
        public ImageView img_item;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_itemName = (TextView) itemView.findViewById(R.id.tv_itemName);
            this.tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            this.tv_size_color = (TextView) itemView.findViewById(R.id.tv_size_color);
            this.tv_quantity = (TextView) itemView.findViewById(R.id.tv_quantity);
            this.tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            this.morePopup = (ImageView) itemView.findViewById(R.id.morePopup);
            this.img_item = (ImageView) itemView.findViewById(R.id.img_item);
        }
    }

    public void ViewClickListner(OnViewClick click) {
        this.viewClick = click;

    }


    public interface OnViewClick {
        void ondeleteitem(int position, String id);

        void onfav(int position);

        void ontotalPrice(ArrayList<CartlistData> price);

        void getCartData(ArrayList<CartlistData> cartId);
    }


}