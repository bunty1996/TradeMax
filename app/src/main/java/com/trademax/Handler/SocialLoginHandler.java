package com.trademax.Handler;

import com.trademax.Models.SocialLogin.SocialLoginExample;

public interface SocialLoginHandler {

    public void onSuccess(SocialLoginExample socialLoginExample);
    public void onError(String message);
}
