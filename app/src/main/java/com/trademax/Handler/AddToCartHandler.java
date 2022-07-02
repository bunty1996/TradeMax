package com.trademax.Handler;

import com.trademax.Models.AddToCart.AddToCartExample;

public interface AddToCartHandler {
    public void onSuccess(AddToCartExample addtocartexample);
    public void onError(String message);
}
