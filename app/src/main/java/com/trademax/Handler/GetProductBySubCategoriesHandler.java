package com.trademax.Handler;


import com.trademax.Models.ProductBySubCategories.ProductBySubCatExample;

public interface GetProductBySubCategoriesHandler {

    public void onSuccess(ProductBySubCatExample productBySubCatExample);
    public void onError(String message);

}
