package com.trademax.Handler;

import com.trademax.Models.GetAddress.GetAddressExample;

public interface GetAddressHandler {

    public void onSuccess(GetAddressExample getAddressExample);
    public void onError(String message);
}
