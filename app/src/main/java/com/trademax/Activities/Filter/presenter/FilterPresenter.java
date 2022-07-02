package com.trademax.Activities.Filter.presenter;

import android.app.Activity;

import com.trademax.Activities.Filter.view.Filterview;
import com.trademax.Handler.FilterHandler;
import com.trademax.Models.Filter.FilterExample;
import com.trademax.Utils.WebServices;

public class FilterPresenter {
    private Activity activity;
    private Filterview filterview;


    public FilterPresenter(Activity activity, Filterview filterview) {
        this.activity = activity;
        this.filterview = filterview;

    }

    public void getfilterproduct(String size,String minprice,String maxprice,String color) {
        filterview.showDialog(activity);
        WebServices.getmInstance().getfilterproduct(size,
                minprice,maxprice,color, new FilterHandler() {
                    @Override
                    public void onSuccess(FilterExample filterExample) {
                        filterview.hideDialog();
                        if (filterExample != null) {
                            if (filterExample.getIsSuccess() == true) {
                                filterview.showMessage(activity, filterExample.getMessage());
                            } else {
                                filterview.showMessage(activity, filterExample.getMessage());
                            }
                        } else {
                            filterview.showMessage(activity, filterExample.getMessage());
                        }
                    }
                    @Override
                    public void onError(String message) {
                        filterview.hideDialog();
                        filterview.showMessage(activity, message);
                    }
                });
    }
    

}
