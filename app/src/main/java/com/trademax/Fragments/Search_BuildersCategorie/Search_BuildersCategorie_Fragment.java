package com.trademax.Fragments.Search_BuildersCategorie;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trademax.Fragments.Search_BuildersCategorie.presenter.Search_Builder_CategoriePresenter;
import com.trademax.Fragments.Search_BuildersCategorie.view.Search_Builder_CategorieView;
import com.trademax.R;
import com.trademax.Utils.Utils;

public class Search_BuildersCategorie_Fragment extends Fragment implements Search_Builder_CategorieView {

    private View view;
    private Activity activity;
    private RecyclerView recyclerView;
    private Search_Builder_CategoriePresenter builder_categoriePresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search__painter_categorie_, container, false);
        activity = getActivity();
        init();

        builder_categoriePresenter = new Search_Builder_CategoriePresenter(activity,this,recyclerView);

        builder_categoriePresenter.getCategoriesListMethod();

        return view;
    }
    private void init() {
        recyclerView = (RecyclerView) view.findViewById(R.id.plaster_recyclerview);
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