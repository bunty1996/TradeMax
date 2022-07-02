package com.trademax.Handler;

import com.trademax.Models.ForgotPass.ForgotPassExample;

public interface ForgotPassHandler {

    public void onSuccess(ForgotPassExample forgotPassExample);
    public void onError(String message);
}
