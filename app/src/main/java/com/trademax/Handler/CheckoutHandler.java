package com.trademax.Handler;

import com.trademax.Models.CheckoutModel.CheckoutExample;

public interface CheckoutHandler {
    public void onSuccess(CheckoutExample checkoutExample);
    public void onError(String message);
}
