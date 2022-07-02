package com.trademax.Handler;


import com.trademax.Models.Login.LoginExample;

public interface LoginHandler {

    public void onSuccess(LoginExample loginExample);
    public void onError(String message);
}
