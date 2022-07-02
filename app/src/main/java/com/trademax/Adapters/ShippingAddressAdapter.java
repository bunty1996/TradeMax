package com.trademax.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.trademax.Activities.AddShippingAddress.AddShippingAddressActivity;
import com.trademax.Activities.ShippingAddress.ShippingAddressActivity;
import com.trademax.Models.GetAddress.GetAddressDatum;
import com.trademax.R;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.google.gson.Gson;

import java.util.List;

public class ShippingAddressAdapter extends RecyclerView.Adapter<ShippingAddressAdapter.ViewHolder> {
    private static final String TAG = "ShippingAddressAdapter";
    private List<GetAddressDatum> listdata;
    private Activity activity;
    private int selectedPosition = 0;
    private int isMyValueChecked;


    // RecyclerView recyclerView;
    public ShippingAddressAdapter(List<GetAddressDatum> listdata, Activity activity) {
        this.listdata = listdata;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.shipping_address_item, parent, false);
        ShippingAddressAdapter.ViewHolder viewHolder = new ShippingAddressAdapter.ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ShippingAddressAdapter.ViewHolder holder, int position) {

        holder.txt_name.setText(listdata.get(position).getFullName());
        holder.txt_address.setText(listdata.get(position).getAddress());
        holder.tv_city_state.setText(listdata.get(position).getCity() + " , " + listdata.get(position).getState());

        holder.checkBox.setOnClickListener(view -> {

            boolean checked = ((CheckBox) view).isChecked();
            if (checked) {
                SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("checkbox", position);
                editor.commit();

                Log.d(TAG, "onBindViewHo" + position);

            } else {
                holder.checkBox.setChecked(false);
            }

            CSPreferences.putString(activity, Utils.GETFULLADDRESS, listdata.get(position).getFullName());
            CSPreferences.putString(activity, Utils.GETADDRESS, listdata.get(position).getAddress());
            CSPreferences.putString(activity, Utils.GETCITY, listdata.get(position).getCity());
            CSPreferences.putString(activity, Utils.GETSTATE, listdata.get(position).getState());
            CSPreferences.putString(activity, Utils.GETADDRESSID, listdata.get(position).getId());

            selectedPosition = holder.getAdapterPosition();
            notifyDataSetChanged();
            activity.onBackPressed();

        });
        if (selectedPosition == position) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }

        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        isMyValueChecked = sharedPref.getInt("checkbox", isMyValueChecked);
        Log.d(TAG, "onBin" + isMyValueChecked);
        if (isMyValueChecked == position) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }


        holder.txt_editAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String data = new Gson().toJson(listdata.get(position));
                Intent intent = new Intent(activity, AddShippingAddressActivity.class);
                intent.putExtra("arraylistData", data);
                activity.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_editAddress;
        public CheckBox checkBox;
        public TextView txt_name;
        public TextView txt_address;
        public TextView tv_city_state;

        public ViewHolder(View itemView) {
            super(itemView);
            this.checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            this.txt_editAddress = (TextView) itemView.findViewById(R.id.txt_editAddress);
            this.txt_name = (TextView) itemView.findViewById(R.id.txt_name);
            this.txt_address = (TextView) itemView.findViewById(R.id.txt_address);
            this.tv_city_state = (TextView) itemView.findViewById(R.id.tv_city_state);
        }
    }

}