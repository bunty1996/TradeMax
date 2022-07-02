package com.trademax.Fragments.Home;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.trademax.Activities.HomeBottomNavigationActivity;
import com.trademax.Adapters.HomeGridAdapter;
import com.trademax.Adapters.SliderAdapter;
import com.trademax.Fragments.Home.presenter.HomePresenter;
import com.trademax.Fragments.Home.view.HomeView;
import com.trademax.Models.GetBanner.BannerDatum;
import com.trademax.R;
import com.trademax.Utils.Utils;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment implements View.OnClickListener, HomeView {

    private View view;
    //    private ImageButton btnNext, btnFinish;
    private HomeGridAdapter myRecyclerAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    private GridView homedetails;

    private ViewPager viewPager;
    private TabLayout indicator;

    private List<Integer> mImageResources;

    Activity activity;

    private HomePresenter homePresenter;
    private List<BannerDatum> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        activity = getActivity();
        init();

        HomeBottomNavigationActivity.linear_toolbar.setVisibility(View.GONE);
        setReference();
        homePresenter = new HomePresenter(activity, this);
        homePresenter.getBannersMethod();
        homePresenter.getHomeDataMethod(homedetails);

        return view;
    }

    private void init() {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        indicator = (TabLayout) view.findViewById(R.id.indicator);
        homedetails = view.findViewById(R.id.idGVcourses);
    }

    public void setReference() {

//        mImageResources = new ArrayList<Integer>();
//        mImageResources.add(R.drawable.paint1);
//        mImageResources.add(R.drawable.paint2);
//        mImageResources.add(R.drawable.painter2);
//        homedetails = view.findViewById(R.id.idGVcourses);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 2000, 4000);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public void showDialog(Activity activity) {

        Utils.showDialogMethod(activity, activity.getFragmentManager());
    }

    @Override
    public void hideDialog() {
        Utils.hideDialog();
    }

    @Override
    public void showMessage(Activity activity, String message) {
        Utils.showMessage(activity, message);
    }

    @Override
    public void setBannerImages(List<BannerDatum> data) {
        this.data = data;
        viewPager.setAdapter(new SliderAdapter(activity, data));
        indicator.setupWithViewPager(viewPager, true);
    }

    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < data.size() - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}