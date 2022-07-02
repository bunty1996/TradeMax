package com.trademax.Handler;

import com.trademax.Models.GetOrders.GetOrderExample;

public interface GetOrdersHandler {

    public void onSuccess(GetOrderExample getOrderExample);
    public void onError(String message);
}
