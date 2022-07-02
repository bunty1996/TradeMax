package com.trademax.Fragments.Search_Painter_PlastersCategorie;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trademax.Fragments.Search_Painter_PlastersCategorie.presenter.Search_Painter_PlastersCategoriePresenter;
import com.trademax.Fragments.Search_Painter_PlastersCategorie.view.Search_Painter_PlastersCategorieView;
import com.trademax.R;
import com.trademax.Utils.Utils;


public class Search_Painter_PlastersCategorie_Fragment extends Fragment implements Search_Painter_PlastersCategorieView {

    private View view;
    private Activity activity;
    private RecyclerView recyclerView;
    private Search_Painter_PlastersCategoriePresenter plastersCategoriePresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_searchpainter_categorie_, container, false);
        activity = getActivity();
        init();
        listiners();

        plastersCategoriePresenter = new Search_Painter_PlastersCategoriePresenter(activity,this,recyclerView);

        plastersCategoriePresenter.getCategoriesListMethod();


        return view;
    }

    private void listiners() {

    }

    private void init() {
        recyclerView = (RecyclerView) view.findViewById(R.id.painter_recyclerview);
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
}