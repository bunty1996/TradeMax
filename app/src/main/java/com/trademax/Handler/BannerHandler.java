package com.trademax.Handler;

import com.trademax.Models.GetBanner.BannerExample;

public interface BannerHandler {

    public void onSuccess(BannerExample bannerExample);
    public void onError(String message);
}
