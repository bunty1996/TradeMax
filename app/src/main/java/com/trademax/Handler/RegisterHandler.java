package com.trademax.Handler;


import com.trademax.Models.Register.SignUpExample;

public interface RegisterHandler {

    public void onSuccess(SignUpExample signUpExample);
    public void onError(String message);
}
