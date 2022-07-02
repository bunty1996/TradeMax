package com.trademax.Handler;

import com.trademax.Models.CurrentUserData.CurrentUserExample;

public interface CurrentUserHandler {

    public void onSuccess(CurrentUserExample currentUserExample);
    public void onError(String message);
}
