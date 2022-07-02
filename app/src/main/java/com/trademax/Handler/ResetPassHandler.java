package com.trademax.Handler;


import com.trademax.Models.ResetPass.ResetPaswordExample;

public interface ResetPassHandler {

    public void onSuccess(ResetPaswordExample resetPaswordExample);
    public void onError(String message);
}
