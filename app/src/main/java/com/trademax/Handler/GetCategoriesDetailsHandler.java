package com.trademax.Handler;

import com.trademax.Models.GetCategoriesDetails.GetCategoriesDetailsExample;

public interface GetCategoriesDetailsHandler {

    public void onSuccess(GetCategoriesDetailsExample getCategoriesDetailsExample);
    public void onError(String message);
}
