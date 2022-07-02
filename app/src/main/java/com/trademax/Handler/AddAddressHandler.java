package com.trademax.Handler;

import com.trademax.Models.AddAddress.AddAddressExample;

public interface AddAddressHandler {

    public void onSuccess(AddAddressExample addAddressExample);
    public void onError(String message);
}
