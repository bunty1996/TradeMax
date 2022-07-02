package com.trademax.Handler;

import com.trademax.Models.ProfileUpdate.ProfileupdateExample;

public interface UpdateProfileHandler {

    public void onSuccess(ProfileupdateExample profileupdateExample);
    public void onError(String message);
}
