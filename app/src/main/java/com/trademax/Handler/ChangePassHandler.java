package com.trademax.Handler;

import com.trademax.Models.ChangePassword.ChangePassExample;

public interface ChangePassHandler {

    public void onSuccess(ChangePassExample changePassExample);
    public void onError(String message);
}
