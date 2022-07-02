package com.trademax.Fragments.Search_BuildersCategorie.presenter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.trademax.Activities.Builder.BuilderActivity;
import com.trademax.Adapters.BuilderTabAdapter;
import com.trademax.Fragments.Search_BuildersCategorie.view.Search_Builder_CategorieView;
import com.trademax.Handler.GetCategoriesDetailsHandler;
import com.trademax.Models.GetCategoriesDetails.GetCategoriesDetailsDatum;
import com.trademax.Models.GetCategoriesDetails.GetCategoriesDetailsExample;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;

import java.util.List;

public class Search_Builder_CategoriePresenter {
    private static final String TAG = "Search_Builder_Categori";

    private Activity activity;
    private Search_Builder_CategorieView plastersCategorieView;
    private RecyclerView recyclerView;

    public Search_Builder_CategoriePresenter(Activity activity, Search_Builder_CategorieView plastersCategorieView, RecyclerView recyclerView) {
        this.activity = activity;
        this.plastersCategorieView = plastersCategorieView;
        this.recyclerView = recyclerView;

    }
    //searchlist
    public void getCategoriesListMethod() {

        String categoriesId = CSPreferences.readString(activity, Utils.CATEGORIESID1);
        String categoriesId2 = CSPreferences.readString(activity, Utils.CATEGORIESID2);
        WebServices.getmInstance().getCategoriesDetailsMethod(categoriesId2, new GetCategoriesDetailsHandler() {
            @Override
            public void onSuccess(GetCategoriesDetailsExample getCategoriesDetailsExample) {
                plastersCategorieView.hideDialog();
                if (getCategoriesDetailsExample != null) {
                    if (getCategoriesDetailsExample.getIsSuccess() == true) {
                        addData(getCategoriesDetailsExample.getData());

                    } else {
                        plastersCategorieView.showMessage(activity, getCategoriesDetailsExample.getMessage());
                    }
                }
            }

            @Override
            public void onError(String message) {
                plastersCategorieView.hideDialog();
            }
        });

    }

    private void addData(List<GetCategoriesDetailsDatum> getCategoriesDetailsData){
        BuilderTabAdapter builderTabAdapter = new BuilderTabAdapter(getCategoriesDetailsData, activity);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(builderTabAdapter);
        builderTabAdapter.ViewClickListner(new BuilderTabAdapter.OnViewClick() {
            @Override
            public void onid(int position) {
                String data = new Gson().toJson(getCategoriesDetailsData.get(position));
                Intent intent = new Intent(activity, BuilderActivity.class);
                intent.putExtra("arraylistData", data);
                activity.startActivity(intent);
                Log.d(TAG, "onid"+data);
            }
        });

    }
}