package com.trademax.Handler;

import com.trademax.Models.Favourite.FavouriteExample;

public interface FavouriteHandler {
    public void onSuccess(FavouriteExample favouriteExample);
    public void onError(String message);
}
