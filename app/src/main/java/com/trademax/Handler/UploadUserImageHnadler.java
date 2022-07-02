package com.trademax.Handler;

import com.trademax.Models.UploadUserImage.UploadUserImageExample;

public interface UploadUserImageHnadler {
    public void onSuccess(UploadUserImageExample uploadUserImageExample);
    public void onError(String message);
}
