package com.trademax.Activities.AddNewCard.presenter;

import android.app.Activity;
import android.content.Intent;

import com.trademax.Activities.AddNewCard.view.TransectionView;
import com.trademax.Activities.Checkout.Presenter.CheckoutPresenter;
import com.trademax.Activities.SuccessScreen.SuccessActivity;
import com.trademax.Handler.TransectionHandler;
import com.trademax.Models.Transections.TransectionsExample;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;


public class TransectionPresenter {

    private Activity activity;
    private String tokenId;
    private TransectionView transectionView;

    public TransectionPresenter(Activity activity, TransectionView transectionView) {
        this.activity = activity;
        this.transectionView = transectionView;
    }

    public void getTransectionToken(String tokenId, String amount) {

        this.tokenId = "tok_visa" + tokenId;
        String deviceToken = CSPreferences.readString(activity, Utils.TOKEN);
        transectionView.showDialog(activity);
        WebServices.getmInstance().getTransectionMethod(deviceToken, "60c90c36414b0a4bfe75b06b", amount, "", "tok_visa", new TransectionHandler() {
            @Override
            public void onSuccess(TransectionsExample transectionsExample) {
                transectionView.hideDialog();
                if (transectionsExample != null) {
                    if (transectionsExample.getIsSuccess() == true) {
                        transectionView.showMessage(activity, String.valueOf(transectionsExample.getMessage()));

                        Intent intent = new Intent(activity, SuccessActivity.class);
                        activity.startActivity(intent);

                    } else {
                        transectionView.showMessage(activity, String.valueOf(transectionsExample.getMessage()));
                    }
                }
            }

            @Override
            public void onError(String message) {
                transectionView.hideDialog();
                transectionView.showMessage(activity, message);
            }
        });

    }
}
