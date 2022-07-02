package com.trademax.Handler;

import com.trademax.Models.VerifyOTP.VerifyOTPExample;

public interface VerifyOTPHandler {

    public void onSuccess(VerifyOTPExample verifyOTPExample);
    public void onError(String message);
}
