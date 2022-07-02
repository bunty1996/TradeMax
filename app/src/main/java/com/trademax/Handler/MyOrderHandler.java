package com.trademax.Handler;

import com.trademax.Models.MyOrder.MyOrderExample;

public interface MyOrderHandler {
    public void onSuccess(MyOrderExample myOrderExample);
    public void onError(String message);
}
