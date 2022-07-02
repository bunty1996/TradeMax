package com.trademax.Handler;

import com.trademax.Models.Filter.FilterExample;

public interface FilterHandler {
    public void onSuccess(FilterExample filterExample);
    public void onError(String message);
}
