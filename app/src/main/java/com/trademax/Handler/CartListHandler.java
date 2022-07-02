package com.trademax.Handler;

import com.trademax.Models.CartListModel.CartlistExample;

public interface CartListHandler {
    public void onSuccess(CartlistExample cartlistExample);
    public void onError(String message);
}
