package com.trademax.Fragments.Favorites;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.trademax.Activities.HomeBottomNavigationActivity;
import com.trademax.Fragments.Favorites.presenter.FavouritePresenter;
import com.trademax.Fragments.Favorites.view.FavouriteView;
import com.trademax.Models.Favourite.FavouriteDatum;
import com.trademax.R;
import com.trademax.Utils.Utils;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment implements AdapterView.OnItemSelectedListener, FavouriteView {

    private View view;
    private Activity activity;
    private GridView favouriteitemGrid;
    private FavouritePresenter favouritePresenter;
    private ArrayList<FavouriteDatum> list;
    private LinearLayout linear_emptyWishlist;


    String[] courses = {"LOW-TO-HIGH", "HIGH-TO-LOW", "NEWEST", "POPULAR"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_favourite, container, false);
        activity = getActivity();
        init();
        listiners();


        list = new ArrayList<>();
        HomeBottomNavigationActivity.linear_toolbar.setVisibility(View.VISIBLE);
        HomeBottomNavigationActivity.tool_text.setVisibility(View.VISIBLE);
        HomeBottomNavigationActivity.tool_text.setText("Favourite");
//
        favouritePresenter = new FavouritePresenter(activity, this, favouriteitemGrid,linear_emptyWishlist);

        return view;
    }

    private void listiners() {

    }

    private void init() {
        linear_emptyWishlist = view.findViewById(R.id.linear_emptyWishlist);
        favouriteitemGrid = view.findViewById(R.id.grid_favourite);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Utils.showDialogMethod(activity, activity.getFragmentManager());
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
    public void onResume() {
        super.onResume();
        favouritePresenter.getFavoriteDataMethod();
    }
}