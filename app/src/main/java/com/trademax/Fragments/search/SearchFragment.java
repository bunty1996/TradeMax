package com.trademax.Fragments.search;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trademax.Activities.HomeBottomNavigationActivity;
import com.trademax.Adapters.SearchTabAdapter;
import com.trademax.Fragments.search.presenter.SearchCategoriesDetailsPresenter;
import com.trademax.Fragments.search.view.SearchCategoriesDetails_View;
import com.trademax.R;
import com.trademax.Utils.Utils;
import com.google.android.material.tabs.TabLayout;

public class SearchFragment extends Fragment implements SearchCategoriesDetails_View {

    private View view;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Activity activity;
    public static TextView categories_title;
    public static TextView categories_subtitle;
    private SearchTabAdapter searchTabAdapter;
    private SearchCategoriesDetailsPresenter searchCategoriesDetailsPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);
        activity = getActivity();

        HomeBottomNavigationActivity.linear_toolbar.setVisibility(View.VISIBLE);

        categories_title = (TextView) view.findViewById(R.id.categories_title);
        categories_subtitle = (TextView) view.findViewById(R.id.categories_subtitle);

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        searchCategoriesDetailsPresenter = new SearchCategoriesDetailsPresenter(activity, this, tabLayout);
        searchCategoriesDetailsPresenter.getSearchCategoriDataMethod();

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0) {
                    categories_title.setText("Colours & Textures");
                    categories_subtitle.setText("Up to 50% off");

                } else {
                    categories_title.setText("Builders & Accessories");
                    categories_subtitle.setText("Up to 50% off");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
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
    public void changeFragment() {

        searchTabAdapter = new SearchTabAdapter(activity, getParentFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(searchTabAdapter);


    }
}