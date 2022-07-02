package com.trademax.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import com.trademax.Fragments.AllOrders.AllOrderFragment;
import com.trademax.Fragments.DeliveredOrders.DeliveredOrderFragment;
import com.trademax.Fragments.OrderedOrders.OrderedOrderFragment;
import com.trademax.Fragments.ShippingOrders.ShippingOrderFragment;
import com.trademax.R;
import com.trademax.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MyOrderTabAdapter extends FragmentPagerAdapter {

    public MyOrderTabAdapter(FragmentManager m) {
        super(m);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        if (position == 0) {
            fragment = new AllOrderFragment();
        } else if (position == 1) {
            fragment = new OrderedOrderFragment();
        } else if (position == 2) {
            fragment = new ShippingOrderFragment();
        } else if (position == 3) {
            fragment = new DeliveredOrderFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = "All";
        } else if (position == 1) {
            title = "Ordered";
        } else if (position == 2) {
            title = "Shipping";
        } else if (position == 3) {
            title = "Delivered";
        }
        return title;
    }
}
