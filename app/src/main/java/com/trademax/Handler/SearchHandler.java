package com.trademax.Handler;

import com.trademax.Models.SearchModel.SearchExample;

public interface SearchHandler {
    public void onSuccess(SearchExample searchExample);
    public void onError(String message);
}
