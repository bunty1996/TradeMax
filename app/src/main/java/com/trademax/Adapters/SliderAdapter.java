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
import com.trademax.Models.GetBanner.BannerDatum;
import com.trademax.R;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    private final List<BannerDatum> mImageResources;
    private Activity activity;

    public SliderAdapter(Activity activity, List<BannerDatum> mImageResources) {
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
        View view = inflater.inflate(R.layout.item_pageindicator, null);

        ImageView img_pager_item = (ImageView) view.findViewById(R.id.img_pager_item);
        Glide.with(activity).load(mImageResources.get(position).getImage()).placeholder(R.drawable.banner1).into(img_pager_item);
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