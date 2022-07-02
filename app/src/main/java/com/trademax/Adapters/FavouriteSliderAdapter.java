package com.trademax.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.trademax.Models.Favourite.FavouriteProductFile;
import com.trademax.R;

import java.util.List;

public class FavouriteSliderAdapter extends PagerAdapter {

    private Activity activity;
    private final List<FavouriteProductFile> mImageResources;


    public FavouriteSliderAdapter(Activity activity, List<FavouriteProductFile> mImageResources) {
        this.activity = activity;
        this.mImageResources = mImageResources;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_pageindicator2, null);

        ImageView img_pager_item = (ImageView) view.findViewById(R.id.img_pager_item);
//        img_pager_item.setImageResource(Integer.parseInt(mImageResources.get(position).getUrl()));
        Glide.with(activity).load(mImageResources.get(position).getUrl()).placeholder(R.drawable.logo).into(img_pager_item);

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }

    @Override
    public int getCount() {
        return mImageResources.size();
    }
}