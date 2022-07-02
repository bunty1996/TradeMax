package com.trademax.Fragments.Search_Painter_PlastersCategorie.presenter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.trademax.Activities.Builder.BuilderActivity;
import com.trademax.Activities.Painters_Plasters.Painter_PlastersActivity;
import com.trademax.Adapters.BuilderTabAdapter;
import com.trademax.Adapters.Painter_PlasterTabAdapter;
import com.trademax.Fragments.Search_Painter_PlastersCategorie.view.Search_Painter_PlastersCategorieView;
import com.trademax.Handler.GetCategoriesDetailsHandler;
import com.trademax.Models.GetCategoriesDetails.GetCategoriesDetailsDatum;
import com.trademax.Models.GetCategoriesDetails.GetCategoriesDetailsExample;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;

import java.util.List;

public class Search_Painter_PlastersCategoriePresenter {


    private Activity activity;
    private Search_Painter_PlastersCategorieView plastersCategorieView;
    private RecyclerView recyclerView;


    public Search_Painter_PlastersCategoriePresenter(Activity activity, Search_Painter_PlastersCategorieView plastersCategorieView, RecyclerView recyclerView) {
        this.activity = activity;
        this.plastersCategorieView = plastersCategorieView;
        this.recyclerView = recyclerView;

    }
    //searchlist
    public void getCategoriesListMethod() {

        String categoriesId = CSPreferences.readString(activity, Utils.CATEGORIESID1);
        String categoriesId2 = CSPreferences.readString(activity, Utils.CATEGORIESID2);
        plastersCategorieView.showDialog(activity);
        WebServices.getmInstance().getCategoriesDetailsMethod(categoriesId, new GetCategoriesDetailsHandler() {
            @Override
            public void onSuccess(GetCategoriesDetailsExample getCategoriesDetailsExample) {
                plastersCategorieView.hideDialog();
                if (getCategoriesDetailsExample != null) {
                    if (getCategoriesDetailsExample.getIsSuccess() == true) {
//                        plastersCategorieView.showMessage(activity, getCategoriesDetailsExample.getMessage());

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

    private void addData(List<GetCategoriesDetailsDatum> categoriesData){

        Painter_PlasterTabAdapter painterTabAdapter = new Painter_PlasterTabAdapter(categoriesData, activity);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(painterTabAdapter);
        painterTabAdapter.ViewClickListner(new Painter_PlasterTabAdapter.OnViewClick() {
            @Override
            public void onid(int position) {
                String data = new Gson().toJson(categoriesData.get(position));
                Intent intent = new Intent(activity, Painter_PlastersActivity.class);
                intent.putExtra("arraylistData", data);
                activity.startActivity(intent);
            }
        });
    }
}
