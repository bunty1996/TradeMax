package com.trademax.Fragments.search.presenter;

import android.app.Activity;
import android.widget.Toast;

import com.trademax.Fragments.Search_Painter_PlastersCategorie.presenter.Search_Painter_PlastersCategoriePresenter;
import com.trademax.Fragments.search.view.SearchCategoriesDetails_View;
import com.trademax.Handler.SearchCategoriesDetailsHandler;
import com.trademax.Models.SearchCategoriesDetailsExample.SearchCategoriesDetailsExample;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;
import com.google.android.material.tabs.TabLayout;

public class SearchCategoriesDetailsPresenter {
    private Activity activity;
    private TabLayout tabLayout;
    private SearchCategoriesDetails_View searchview;
    private Search_Painter_PlastersCategoriePresenter plastersCategoriePresenter;


    public SearchCategoriesDetailsPresenter(Activity activity, SearchCategoriesDetails_View searchview, TabLayout tabLayout) {
        this.activity = activity;
        this.searchview = searchview;
        this.tabLayout = tabLayout;
    }

    //nned to set empty cat id
    public void getSearchCategoriDataMethod() {
        searchview.showDialog(activity);
        WebServices.getmInstance().getSearchMethod("", new SearchCategoriesDetailsHandler() {
            @Override
            public void onSuccess(SearchCategoriesDetailsExample getallSearchExample) {
                searchview.hideDialog();
                if (getallSearchExample != null) {
                    if (getallSearchExample.getIsSuccess() == true) {

                        CSPreferences.putString(activity, Utils.CATEGORIESID1, getallSearchExample.getData().get(0).getId());
                        CSPreferences.putString(activity, Utils.CATEGORIESID2, getallSearchExample.getData().get(1).getId());
                        mainHeadingData(getallSearchExample.getData().get(0).getName(), getallSearchExample.getData().get(1).getName());
                        searchview.changeFragment();

                    } else {
                        searchview.showMessage(activity, getallSearchExample.getMessage());
                    }
                }
            }

            @Override
            public void onError(String message) {
                searchview.hideDialog();
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void mainHeadingData(String first, String second) {
        tabLayout.addTab(tabLayout.newTab().setText(first));
        tabLayout.addTab(tabLayout.newTab().setText(second));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }
}
