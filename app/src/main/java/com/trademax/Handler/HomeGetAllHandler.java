package com.trademax.Handler;

import com.trademax.Models.HomeGetAll.GetAllProductsExample;

public interface HomeGetAllHandler {

    public void onSuccess(GetAllProductsExample getAllProductsExample);
    public void onError(String message);
}
